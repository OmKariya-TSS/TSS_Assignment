package com.tss.FoodAppV3.model;

import com.tss.FoodAppV3.enums.OrderStatus;
import com.tss.FoodAppV3.enums.PaymentMethod;
import com.tss.FoodAppV3.enums.PaymentStatus;
import com.tss.FoodAppV3.exceptions.ValidationException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    private int orderId;
    private Customer customer;
    private Restaurant restaurant;
    private List<OrderItem> items;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    private double subtotal;
    private double discountAmount;
    private double finalTotal;

    private DeliveryAgent assignedAgent;

    private LocalDateTime orderTime;
    private String specialNote;

    public Order(int orderId, Customer customer, Restaurant restaurant) {

        validateCustomer(customer);
        validateRestaurant(restaurant);

        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PLACED;
        this.paymentStatus = PaymentStatus.PENDING;
        this.orderTime = LocalDateTime.now();
    }


    private void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new ValidationException("Customer cannot be null");
        }
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (restaurant == null) {
            throw new ValidationException("Restaurant cannot be null");
        }
    }

    private void validateOrderItem(OrderItem item) {
        if (item == null) {
            throw new ValidationException("Order item cannot be null");
        }
    }

    private void validateDiscount(double discountAmount) {
        if (discountAmount < 0) {
            throw new ValidationException("Discount amount cannot be negative");
        }
    }

    private void validateStatus(OrderStatus status) {
        if (status == null) {
            throw new ValidationException("Order status cannot be null");
        }
    }

    public void addItem(OrderItem item) {
        validateOrderItem(item);
        items.add(item);
        calculateSubtotal();
    }

    public void removeItem(int itemId) {
        items.removeIf(item -> item.getMenuItem().getItemId() == itemId);
        calculateSubtotal();
    }

    public double calculateSubtotal() {
        this.subtotal = items.stream()
                .mapToDouble(OrderItem::getItemTotal)
                .sum();

        this.finalTotal = subtotal - discountAmount;
        return subtotal;
    }

    public void updateStatus(OrderStatus status) {
        validateStatus(status);
        this.status = status;
    }

    public void assignAgent(DeliveryAgent agent) {
        this.assignedAgent = agent;
        this.status = OrderStatus.OUT_FOR_DELIVERY;
    }


    public int getOrderId() { return orderId; }

    public Customer getCustomer() { return customer; }

    public Restaurant getRestaurant() { return restaurant; }

    public List<OrderItem> getItems() { return items; }

    public OrderStatus getStatus() { return status; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }

    public double getSubtotal() { return subtotal; }

    public double getDiscountAmount() { return discountAmount; }

    public double getFinalTotal() { return finalTotal; }

    public DeliveryAgent getAssignedAgent() { return assignedAgent; }

    public LocalDateTime getOrderTime() { return orderTime; }

    public String getSpecialNote() { return specialNote; }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomer(Customer customer) {
        validateCustomer(customer);
        this.customer = customer;
    }

    public void setRestaurant(Restaurant restaurant) {
        validateRestaurant(restaurant);
        this.restaurant = restaurant;
    }

    public void setItems(List<OrderItem> items) {
        if (items == null) {
            throw new ValidationException("Items list cannot be null");
        }
        this.items = items;
        calculateSubtotal();
    }

    public void setStatus(OrderStatus status) {
        validateStatus(status);
        this.status = status;
    }

    public void setSubtotal(double subtotal) {
        if (subtotal < 0) {
            throw new ValidationException("Subtotal cannot be negative");
        }
        this.subtotal = subtotal;
    }

    public void setFinalTotal(double finalTotal) {
        if (finalTotal < 0) {
            throw new ValidationException("Final total cannot be negative");
        }
        this.finalTotal = finalTotal;
    }

    public void setAssignedAgent(DeliveryAgent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        if (orderTime == null) {
            throw new ValidationException("Order time cannot be null");
        }
        this.orderTime = orderTime;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new ValidationException("Payment method cannot be null");
        }
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        if (paymentStatus == null) {
            throw new ValidationException("Payment status cannot be null");
        }
        this.paymentStatus = paymentStatus;
    }

    public void setDiscountAmount(double discountAmount) {
        validateDiscount(discountAmount);
        this.discountAmount = discountAmount;
        calculateSubtotal();
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }


    @Override
    public String toString() {
        return "Order ID: " + orderId +
                "\nCustomer: " + customer.getName() +
                "\nRestaurant: " + restaurant.getName() +
                "\nItems Count: " + items.size() +
                "\nStatus: " + status +
                "\nPayment Status: " + paymentStatus +
                "\nSubtotal: " + subtotal +
                "\nDiscount: " + discountAmount +
                "\nFinal Total: " + finalTotal +
                "\nOrdered At: " + orderTime +
                "\nAssigned Agent: " +
                (assignedAgent != null ? assignedAgent.getName() : "Not Assigned") +
                "\n----------------------------------";
    }
}