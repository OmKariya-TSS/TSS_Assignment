package com.tss.FoodApp.model;

import com.tss.FoodApp.enums.UserRole;
import com.tss.FoodApp.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer extends User {

    private String address;
    private List<Order> orderHistory;

    public Customer() {
    }

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

    @Override
    public void showDashboard() {
        System.out.println("\n=================================");
        System.out.println("       CUSTOMER DASHBOARD        ");
        System.out.println("=================================");
        System.out.println("Welcome, " + getName());
        System.out.println("Delivery Address: " + address);
        System.out.println("=================================\n");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Address: " + address +
                " | Total Orders: " + orderHistory.size();
    }
}