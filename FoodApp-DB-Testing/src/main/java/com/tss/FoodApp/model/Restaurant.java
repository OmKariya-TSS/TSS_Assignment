package com.tss.FoodApp.model;

import com.tss.FoodApp.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Restaurant {

    private int restaurantId;
    private String name;
    private String location;
    private String cuisineType;
    public boolean isOpen;

    private List<MenuItem> menu;
    private List<DeliveryAgent> agents;

    public Restaurant() {
    }

    public Restaurant(int restaurantId,
                      String name,
                      String location,
                      String cuisineType) {

        validateRestaurantId(restaurantId);
        validateName(name);
        validateLocation(location);
        validateCuisineType(cuisineType);

        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
        this.isOpen = true;

        this.menu = new ArrayList<>();
        this.agents = new ArrayList<>();
    }

    private void validateRestaurantId(int restaurantId) {
        if (restaurantId <= 0) {
            throw new ValidationException("Restaurant ID must be positive");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Restaurant name cannot be null or empty");
        }
    }

    private void validateLocation(String location) {
        if (location == null || location.isBlank()) {
            throw new ValidationException("Location cannot be null or empty");
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    private void validateCuisineType(String cuisineType) {
        if (cuisineType == null || cuisineType.isBlank()) {
            throw new ValidationException("Cuisine type cannot be null or empty");
        }
    }

    public void removeMenuItem(int itemId) {
        menu.removeIf(item -> item.getItemId() == itemId);
    }

    public void updateMenuItemPrice(int itemId, double newPrice) {
        findMenuItemById(itemId)
                .ifPresent(item -> item.setPrice(newPrice));
    }

    public Optional<MenuItem> findMenuItemById(int itemId) {
        return menu.stream()
                .filter(item -> item.getItemId() == itemId)
                .findFirst();
    }

    public int getRestaurantId() { return restaurantId; }

    public String getName() { return name; }

    public String getLocation() { return location; }

    public String getCuisineType() { return cuisineType; }

    public List<MenuItem> getMenu() { return menu; }

    public List<DeliveryAgent> getAgents() { return agents; }

    public void setRestaurantId(int restaurantId) {
        validateRestaurantId(restaurantId);
        this.restaurantId = restaurantId;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setLocation(String location) {
        validateLocation(location);
        this.location = location;
    }

    public void setCuisineType(String cuisineType) {
        validateCuisineType(cuisineType);
        this.cuisineType = cuisineType;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setMenu(List<MenuItem> menu) {
        if (menu == null) {
            throw new ValidationException("Menu list cannot be null");
        }
        this.menu = menu;
    }

    public void setAgents(List<DeliveryAgent> agents) {
        if (agents == null) {
            throw new ValidationException("Agents list cannot be null");
        }
        this.agents = agents;
    }

    @Override
    public String toString() {
        return "Restaurant ID: " + restaurantId +
                "\nName: " + name +
                "\nLocation: " + location +
                "\nCuisine: " + cuisineType +
                "\nOpen: " + (isOpen ? "Yes" : "No") +
                "\nMenu Items: " + menu.size() +
                "\nTotal Agents: " + agents.size() +
                "\n----------------------------------";
    }
}