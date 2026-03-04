package com.tss.FoodAppV4.builder;

import com.tss.FoodAppV4.discount.DiscountContext;
import com.tss.FoodAppV4.enums.PaymentMethod;
import com.tss.FoodAppV4.exceptions.ValidationException;
import com.tss.FoodAppV4.factory.OrderFactory;
import com.tss.FoodAppV4.model.*;

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

    public OrderBuilder setPaymentMethod(PaymentMethod paymentMethod) {


        order.setPaymentMethod(paymentMethod);
        return this;
    }

    public OrderBuilder setSpecialNote(String note) {

        if (note != null && note.length() > 250) {
            throw new ValidationException("Special note cannot exceed 250 characters.");
        }

        order.setSpecialNote(note);
        return this;
    }

    public OrderBuilder applyDiscount(DiscountContext ctx) {

        if (ctx == null) {
            throw new ValidationException("Discount context cannot be null.");
        }

        double subtotal = order.calculateSubtotal();

        if (subtotal <= 0) {
            throw new ValidationException("Cannot apply discount on empty or zero subtotal.");
        }

        double discount = ctx.applyDiscount(subtotal);

        if (discount < 0) {
            throw new ValidationException("Discount amount cannot be negative.");
        }

        if (discount > subtotal) {
            throw new ValidationException("Discount cannot exceed subtotal.");
        }

        order.setDiscountAmount(discount);
        order.setFinalTotal(subtotal - discount);

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