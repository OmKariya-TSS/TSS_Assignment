package com.tss.FoodApp.builder;

import com.tss.FoodApp.discount.DiscountContext;
import com.tss.FoodApp.enums.PaymentMethod;
import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.factory.OrderFactory;
import com.tss.FoodApp.model.*;
import com.tss.FoodApp.model.*;

public class OrderBuilder {

    private Order order;

    public OrderBuilder(Customer customer, Restaurant restaurant) {

        if (customer == null) {
            throw new ValidationException("Customer cannot be null while creating order.");
        }

        if (restaurant == null) {
            throw new ValidationException("Restaurant cannot be null while creating order.");
        }

        this.order = OrderFactory.createOrder(customer, restaurant);
    }

    public OrderBuilder addItem(MenuItem item, int qty) {

        if (order == null) {
            throw new ValidationException("Order not initialized.");
        }

        if (item == null) {
            throw new ValidationException("MenuItem cannot be null.");
        }

        if (qty <= 0) {
            throw new ValidationException("Quantity must be greater than zero.");
        }

        OrderItem orderItem = new OrderItem(item, qty);
        order.addItem(orderItem);

        return this;
    }
    public Order build() {

        if (order == null) {
            throw new ValidationException("Order not initialized.");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new ValidationException("Order must contain at least one item.");
        }

        double subtotal = order.calculateSubtotal();

        if (subtotal <= 0) {
            throw new ValidationException("Order subtotal must be greater than zero.");
        }

        order.setSubtotal(subtotal);


        if (order.getFinalTotal() <= 0) {
            order.setFinalTotal(subtotal);
        }

        return order;
    }
}