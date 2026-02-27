package com.tss.FoodApp.model;

import com.tss.FoodApp.exceptions.ValidationException;

public class DeliveryAgent {

    private int agentId;
    private String name;
    private String phone;
    private boolean isAvailable;
    private int totalDeliveries;

    public DeliveryAgent(int agentId, String name, String phone) {

        validateName(name);
        validatePhone(phone);

        this.agentId = agentId;
        this.name = name;
        this.phone = phone;
        this.isAvailable = true;
        this.totalDeliveries = 0;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Agent name cannot be null or empty");
        }

        if (name.length() < 3) {
            throw new ValidationException("Agent name must be at least 3 characters long");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new ValidationException("Phone number cannot be null or empty");
        }

        if (!phone.matches("\\d{10}")) {
            throw new ValidationException("Phone number must contain exactly 10 digits");
        }
    }

    public void markBusy() {
        this.isAvailable = false;
    }

    public void markAvailable() {
        this.isAvailable = true;
    }

    public void incrementDeliveries() {
        this.totalDeliveries++;
    }

    public int getAgentId() {
        return agentId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getTotalDeliveries() {
        return totalDeliveries;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "Agent ID: %d | Name: %s | Phone: %s | Status: %s | Total Deliveries: %d",
                agentId,
                name,
                phone,
                isAvailable ? "Available" : "Busy",
                totalDeliveries
        );
    }
}