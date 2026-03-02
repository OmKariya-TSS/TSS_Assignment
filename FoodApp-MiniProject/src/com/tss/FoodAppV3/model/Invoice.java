package com.tss.FoodAppV3.model;

import com.tss.FoodAppV3.exceptions.ValidationException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Invoice implements Serializable {

    private int invoiceId;
    private Order order;
    private Customer customer;
    private Restaurant restaurant;
    private List<OrderItem> items;

    private double subtotal;
    private double discountApplied;
    private double finalAmount;

    private String paymentMethod;
    private DeliveryAgent agent;

    private LocalDateTime generatedAt;

    public Invoice(int invoiceId, Order order) {

        validateOrder(order);

        this.invoiceId = invoiceId;
        this.order = order;

        this.customer = order.getCustomer();
        this.restaurant = order.getRestaurant();
        this.items = order.getItems();

        this.subtotal = order.getSubtotal();
        this.discountApplied = order.getDiscountAmount();
        this.finalAmount = order.getFinalTotal();

        this.paymentMethod = order.getPaymentMethod() != null
                ? order.getPaymentMethod().toString()
                : "N/A";

        this.agent = order.getAssignedAgent();
        this.generatedAt = LocalDateTime.now();
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new ValidationException("Order cannot be null");
        }
    }

    private void validateItems(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new ValidationException("Invoice must contain at least one order item");
        }
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public Order getOrder() {
        return order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public DeliveryAgent getAgent() {
        return agent;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setOrder(Order order) {
        validateOrder(order);
        this.order = order;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setItems(List<OrderItem> items) {
        validateItems(items);
        this.items = items;
    }

    public void setSubtotal(double subtotal) {
        if (subtotal < 0) {
            throw new ValidationException("Subtotal cannot be negative");
        }
        this.subtotal = subtotal;
    }

    public void setDiscountApplied(double discountApplied) {
        if (discountApplied < 0) {
            throw new ValidationException("Discount cannot be negative");
        }
        this.discountApplied = discountApplied;
    }

    public void setFinalAmount(double finalAmount) {
        if (finalAmount < 0) {
            throw new ValidationException("Final amount cannot be negative");
        }
        this.finalAmount = finalAmount;
    }

    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new ValidationException("Payment method cannot be null or empty");
        }
        this.paymentMethod = paymentMethod;
    }

    public void setAgent(DeliveryAgent agent) {
        this.agent = agent;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        if (generatedAt == null) {
            throw new ValidationException("Generated date cannot be null");
        }
        this.generatedAt = generatedAt;
    }

    public void printInvoice() {
        System.out.println(buildHeader());
        System.out.println(buildItemsSection());
        System.out.println(buildTotalsSection());
        System.out.println(buildFooter());
    }

    public String buildHeader() {
        return String.format(
                "\n========================================\n" +
                        "              INVOICE #%d\n" +
                        "========================================\n" +
                        "Restaurant: %s\n" +
                        "Location: %s\n" +
                        "Customer: %s\n" +
                        "Generated At: %s\n" +
                        "----------------------------------------",
                invoiceId,
                restaurant.getName(),
                restaurant.getLocation(),
                customer.getName(),
                generatedAt
        );
    }

    public String buildItemsSection() {

        StringBuilder sb = new StringBuilder();
        sb.append("\nItems:\n");

        for (OrderItem item : items) {
            sb.append(String.format(
                    "%-20s x%-3d  ₹%-8.2f\n",
                    item.getMenuItem(),
                    item.getQuantity(),
                    item.getItemTotal()
            ));
        }

        sb.append("----------------------------------------");
        return sb.toString();
    }

    public String buildTotalsSection() {
        return String.format(
                "\nSubtotal:        ₹%.2f\n" +
                        "Discount:        ₹%.2f\n" +
                        "Final Amount:    ₹%.2f\n" +
                        "Payment Method:  %s\n",
                subtotal,
                discountApplied,
                finalAmount,
                paymentMethod
        );
    }

    public String buildFooter() {
        return String.format(
                "Delivery Agent:  %s\n" +
                        "========================================\n",
                agent != null ? agent.getName() : "Not Assigned"
        );
    }
}