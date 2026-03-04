package com.tss.FoodAppV4.service.interfaces;

import com.tss.FoodAppV4.enums.OrderStatus;
import com.tss.FoodAppV4.model.Customer;
import com.tss.FoodAppV4.model.Order;
import com.tss.FoodAppV4.model.Restaurant;

import java.util.List;

public interface IOrderService {
    Order createOrder(Customer customer, Restaurant restaurant);
    void addItemToOrder(Order order, int itemId, int quantity);
    Order confirmOrder(Order order);
    boolean cancelOrder(int orderId);
    void restoreOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getOrdersByCustomer(int customerId);
    List<Order> getAllOrders();
    void updateOrderStatus(int orderId, OrderStatus status);
    void advanceOrderStatus(int orderId);
    void markDelivered(int orderId);
}