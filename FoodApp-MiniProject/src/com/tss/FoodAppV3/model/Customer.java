package com.tss.FoodAppV3.model;

import com.tss.FoodAppV3.enums.UserRole;
import com.tss.FoodAppV3.exceptions.ValidationException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer extends User implements Serializable {

    private String address;
    private List<Order> orderHistory;

    public Customer(int userId,
                    String name,
                    String email,
                    String password,
                    String phone,
                    String address) {

        super(userId, name, email, password, phone, UserRole.CUSTOMER);

        validateAddress(address);

        this.address = address;
        this.orderHistory = new ArrayList<>();
    }

    private void validateAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new ValidationException("Address cannot be null or empty");
        }

        if (address.length() < 5) {
            throw new ValidationException("Address must be at least 5 characters long");
        }
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new ValidationException("Order cannot be null");
        }
    }

    @Override
    public void showDashboard() {
        System.out.println("\n=================================");
        System.out.println("       CUSTOMER DASHBOARD        ");
        System.out.println("=================================");
        System.out.println("Welcome, " + getName());
        System.out.println("Delivery Address: " + address);
        System.out.println("=================================\n");
    }

    public void addOrderToHistory(Order order) {
        validateOrder(order);
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return Collections.unmodifiableList(orderHistory);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Address: " + address +
                " | Total Orders: " + orderHistory.size();
    }
}