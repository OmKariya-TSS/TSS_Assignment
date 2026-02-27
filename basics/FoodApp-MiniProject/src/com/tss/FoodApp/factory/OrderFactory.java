package com.tss.FoodApp.factory;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.model.Customer;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.model.Restaurant;

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
}