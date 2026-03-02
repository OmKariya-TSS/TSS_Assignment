package com.tss.FoodAppV2.builder;

import com.tss.FoodAppV2.exceptions.ValidationException;
import com.tss.FoodAppV2.model.Invoice;
import com.tss.FoodAppV2.model.Order;

import java.time.LocalDateTime;

public class InvoiceBuilder {

    private Invoice invoice;
    private static int idCounter = 1;

    public InvoiceBuilder setOrder(Order order) {

        if (order == null) {
            throw new ValidationException("Order cannot be null while creating invoice.");
        }

        if (order.getOrderId() <= 0) {
            throw new ValidationException("Invalid Order ID for invoice generation.");
        }

        this.invoice = new Invoice(idCounter++, order);
        return this;
    }

    public InvoiceBuilder setGeneratedAt() {

        if (invoice == null) {
            throw new ValidationException("Order must be set before generating invoice.");
        }

        invoice.setGeneratedAt(LocalDateTime.now());
        return this;
    }

    public InvoiceBuilder calculateTotals() {

        if (invoice == null) {
            throw new ValidationException("Invoice not initialized. Set order first.");
        }

        Order order = invoice.getOrder();

        if (order == null) {
            throw new ValidationException("Invoice contains invalid order reference.");
        }

        double subtotal = order.calculateSubtotal();
        double discount = order.getDiscountAmount();
        double finalAmount = order.getFinalTotal();

        if (subtotal < 0 || finalAmount < 0) {
            throw new ValidationException("Invalid financial calculation detected in Order.");
        }

        invoice.setSubtotal(subtotal);
        invoice.setDiscountApplied(discount);
        invoice.setFinalAmount(finalAmount);

        return this;
    }

    public Invoice build() {

        if (invoice == null) {
            throw new ValidationException("Invoice not properly built. Order not set.");
        }

        if (invoice.getGeneratedAt() == null) {
            throw new ValidationException("Invoice generation time not set.");
        }

        if (invoice.getFinalAmount() <= 0) {
            throw new ValidationException("Invoice final amount must be greater than zero.");
        }

        return invoice;
    }
}