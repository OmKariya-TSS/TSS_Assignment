package com.tss.FoodApp.service.implementations;

import com.tss.FoodApp.discount.DiscountContext;
import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.enums.PaymentStatus;
import com.tss.FoodApp.exceptions.InvalidOrderOperationException;
import com.tss.FoodApp.exceptions.OrderNotFoundException;
import com.tss.FoodApp.exceptions.RestaurantNotFoundException;
import com.tss.FoodApp.factory.OrderFactory;
import com.tss.FoodApp.model.*;
import com.tss.FoodApp.observer.OrderEventManager;
import com.tss.FoodApp.repository.interfaces.IOrderRepository;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.service.interfaces.IOrderService;

import java.util.List;

public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepo;
    private final IRestaurantRepository restaurantRepo;
    private final DiscountContext discountContext;
    private final OrderEventManager eventManager;

    public OrderServiceImpl(IOrderRepository orderRepo,
                            IRestaurantRepository restaurantRepo,
                            DiscountContext discountContext,
                            OrderEventManager eventManager) {

        if (orderRepo == null || restaurantRepo == null) {
            throw new IllegalArgumentException("Repositories cannot be null");
        }

        this.orderRepo = orderRepo;
        this.restaurantRepo = restaurantRepo;
        this.discountContext = discountContext;
        this.eventManager = eventManager;
    }

    @Override
    public Order createOrder(Customer customer, Restaurant restaurant) {

        if (customer == null || restaurant == null) {
            throw new IllegalArgumentException("Customer or Restaurant cannot be null");
        }

        return OrderFactory.createOrder(customer, restaurant);
    }

    @Override
    public void addItemToOrder(Order order, int itemId, int quantity) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (quantity <= 0) {
            throw new InvalidOrderOperationException("Quantity must be greater than 0");
        }

        Restaurant restaurant = restaurantRepo.findById(
                order.getRestaurant().getRestaurantId()
        ).orElseThrow(() ->
                new RestaurantNotFoundException("Restaurant not found")
        );

        MenuItem item = restaurant.findMenuItemById(itemId)
                .orElseThrow(() ->
                        new InvalidOrderOperationException("Menu item not found ID: " + itemId)
                );

        order.setPaymentStatus(PaymentStatus.PENDING);
        order.addItem(new OrderItem(item, quantity));

        System.out.println("✅ Added " + quantity + " × " + item.getName());
    }

    @Override
    public Order confirmOrder(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (order.getItems().isEmpty()) {
            throw new InvalidOrderOperationException("Cannot confirm empty order");
        }

        order.updateStatus(OrderStatus.CONFIRMED);
        orderRepo.save(order);
        notify(order, OrderStatus.CONFIRMED);

        order.setPaymentStatus(PaymentStatus.SUCCESS);

        System.out.println("✅ Order #" + order.getOrderId()
                + " confirmed. Total: ₹" + order.getFinalTotal());

        return order;
    }

    @Override
    public boolean cancelOrder(int orderId) {

        Order order = getOrderById(orderId);

        if (order.getStatus().isFinal()) {
            throw new InvalidOrderOperationException(
                    "Cannot cancel a " + order.getStatus() + " order."
            );
        }

        order.updateStatus(OrderStatus.CANCELLED);
        orderRepo.update(order);
        order.setPaymentStatus(PaymentStatus.REFUNDED);

        notify(order, OrderStatus.CANCELLED);

        if (order.getAssignedAgent() != null) {
            order.getAssignedAgent().markAvailable();
        }

        System.out.println("↩ Order #" + orderId + " cancelled.");
        return true;
    }

    @Override
    public void restoreOrder(Order order) {
        order.updateStatus(OrderStatus.PLACED);
        orderRepo.update(order);
        notify(order, OrderStatus.PLACED);
        System.out.println("↩ Order #" + order.getOrderId() + " restored.");
    }

    @Override
    public void advanceOrderStatus(int orderId) {

        Order order = getOrderById(orderId);

        OrderStatus current = order.getStatus();

        if (current.isFinal()) {
            throw new InvalidOrderOperationException(
                    "Order already in final state: " + current
            );
        }

        OrderStatus next = current.next();

        order.updateStatus(next);
        orderRepo.update(order);
        notify(order, next);

        System.out.println("✅ Order #" + orderId + " → " + next);
    }

    @Override
    public void markDelivered(int orderId) {

        Order order = getOrderById(orderId);

        if (order.getStatus() != OrderStatus.OUT_FOR_DELIVERY) {
            throw new InvalidOrderOperationException(
                    "Order is not OUT_FOR_DELIVERY. Current: "
                            + order.getStatus()
            );
        }

        order.updateStatus(OrderStatus.DELIVERED);
        order.setPaymentStatus(PaymentStatus.SUCCESS);
        orderRepo.update(order);

        DeliveryAgent agent = order.getAssignedAgent();

        if (agent != null) {
            agent.markAvailable();
            agent.incrementDeliveries();
        }

        notify(order, OrderStatus.DELIVERED);

        System.out.println("📦 Order #" + orderId + " marked as DELIVERED.");
    }

    @Override
    public void updateOrderStatus(int orderId, OrderStatus status) {

        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        Order order = getOrderById(orderId);

        order.updateStatus(status);
        orderRepo.update(order);
        notify(order, status);

        System.out.println("ℹ Order #" + orderId + " → " + status);
    }

    @Override
    public Order getOrderById(int orderId) {

        if (orderId <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        return orderRepo.findById(orderId)
                .orElseThrow(() ->
                        new OrderNotFoundException(
                                "Order not found ID: " + orderId
                        )
                );
    }

    @Override
    public List<Order> getOrdersByCustomer(int customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }

        return orderRepo.findByCustomerId(customerId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    private void notify(Order order, OrderStatus status) {
        if (eventManager != null) {
            eventManager.notifyObservers(order, status);
        }
    }
}