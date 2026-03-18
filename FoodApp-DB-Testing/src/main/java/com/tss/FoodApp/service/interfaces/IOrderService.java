package com.tss.FoodApp.service.interfaces;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.model.Customer;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.model.Restaurant;

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