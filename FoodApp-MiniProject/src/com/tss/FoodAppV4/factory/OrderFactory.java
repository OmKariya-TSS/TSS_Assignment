package com.tss.FoodAppV4.factory;

import com.tss.FoodAppV4.enums.OrderStatus;
import com.tss.FoodAppV4.model.Customer;
import com.tss.FoodAppV4.model.Order;
import com.tss.FoodAppV4.model.Restaurant;

import java.time.LocalDateTime;

public class OrderFactory {
    private static int idCounter = 1;
    public static Order createOrder(Customer customer, Restaurant restaurant) {
        Order order = new Order(
                idCounter++,
                customer,
                restaurant
        );
        order.setStatus(OrderStatus.PLACED);
        order.setOrderTime(LocalDateTime.now());
        return order;
    }
    public static void setIdCounter(int value) {
        idCounter = value;
    }
}