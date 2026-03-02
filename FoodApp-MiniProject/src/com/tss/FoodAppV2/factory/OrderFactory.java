package com.tss.FoodAppV2.factory;

import com.tss.FoodAppV2.enums.OrderStatus;
import com.tss.FoodAppV2.model.Customer;
import com.tss.FoodAppV2.model.Order;
import com.tss.FoodAppV2.model.Restaurant;

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