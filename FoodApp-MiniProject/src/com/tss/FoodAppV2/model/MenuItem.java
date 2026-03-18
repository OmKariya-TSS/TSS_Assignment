package com.tss.FoodAppV2.model;

import com.tss.FoodAppV2.enums.MenuCategory;
import com.tss.FoodAppV2.exceptions.ValidationException;

import java.util.Objects;

public class MenuItem {

    private final int itemId;
    private final String name;
    private double price;
    private final MenuCategory category;
    private boolean isAvailable;
    private String description;

    public MenuItem(int itemId,
                    String name,
                    double price,
                    MenuCategory category,
                    String description) {

        this.itemId = validateItemId(itemId);
        this.name = validateName(name);
        this.price = validatePrice(price);
        this.category = category;
        this.description = validateDescription(description);

        this.isAvailable = true;
    }



    private int validateItemId(int itemId) {
        if (itemId <= 0) {
            throw new ValidationException("Item ID must be positive.");
        }
        return itemId;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Item name cannot be empty.");
        }
        return name.trim();
    }

    private double validatePrice(double price) {
        if (price <= 0) {
            throw new ValidationException("Price must be greater than 0.");
        }
        return price;
    }


    private String validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new ValidationException("Description cannot be empty.");
        }
        return description.trim();
    }


    public int getItemId() { return itemId; }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public MenuCategory getCategory() { return category; }

    public boolean isAvailable() { return isAvailable; }

    public String getDescription() { return description; }


    public void setPrice(double price) {
        this.price = validatePrice(price);
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void setDescription(String description) {
        this.description = validateDescription(description);
    }


    public void display() {
        System.out.printf(
                "%-5d %-20s ₹%-10.2f %-15s %-15s %s%n",
                itemId,
                name,
                price,
                category,
                isAvailable ? "Available" : "Not Available",
                description
        );
    }


    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", isAvailable=" + isAvailable +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem that = (MenuItem) o;
        return itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }
}