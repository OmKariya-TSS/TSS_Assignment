package com.tss.FoodAppV4.model;

import com.tss.FoodAppV4.exceptions.ValidationException;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private MenuItem menuItem;
    private int quantity;
    private double itemTotal;

    public OrderItem(MenuItem menuItem, int quantity) {

        validateMenuItem(menuItem);
        validateQuantity(quantity);

        this.menuItem = menuItem;
        this.quantity = quantity;
        calculateTotal();
    }
    private void validateMenuItem(MenuItem menuItem) {
        if (menuItem == null) {
            throw new ValidationException("MenuItem cannot be null");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new ValidationException("Quantity must be greater than 0");
        }
    }

    public double calculateTotal() {
        this.itemTotal = menuItem.getPrice() * quantity;
        return itemTotal;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
        calculateTotal();
    }

    @Override
    public String toString() {
        return String.format(
                "%-20s x %-3d = ₹%.2f",
                menuItem.getName(),
                quantity,
                itemTotal
        );
    }
}