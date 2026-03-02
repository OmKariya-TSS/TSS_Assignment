package com.tss.FoodAppV3.factory;

import com.tss.FoodAppV3.enums.OrderStatus;
import com.tss.FoodAppV3.model.Customer;
import com.tss.FoodAppV3.model.Order;
import com.tss.FoodAppV3.model.Restaurant;

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