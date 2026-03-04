package com.tss.FoodAppV4.model;

import com.tss.FoodAppV4.exceptions.ValidationException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Restaurant implements Serializable {

    private int restaurantId;
    private String name;
    private String location;
    private String cuisineType;
    private boolean isOpen;

    private List<MenuItem> menu;
    private List<DeliveryAgent> agents;

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

    private void validateCuisineType(String cuisineType) {
        if (cuisineType == null || cuisineType.isBlank()) {
            throw new ValidationException("Cuisine type cannot be null or empty");
        }
    }

    private void validateMenuItem(MenuItem item) {
        if (item == null) {
            throw new ValidationException("Menu item cannot be null");
        }
    }

    private void validateAgent(DeliveryAgent agent) {
        if (agent == null) {
            throw new ValidationException("Agent cannot be null");
        }
    }


    public void addMenuItem(MenuItem item) {
        validateMenuItem(item);
        menu.add(item);
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

    public void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu is currently empty.");
            return;
        }

        System.out.println("\n====== MENU - " + name + " ======");
        menu.forEach(MenuItem::display);
        System.out.println("===============================\n");
    }

    public void addAgent(DeliveryAgent agent) {
        validateAgent(agent);
        agents.add(agent);
    }

    public void removeAgent(int agentId) {
        agents.removeIf(agent -> agent.getAgentId() == agentId);
    }

    public List<DeliveryAgent> getAvailableAgents() {
        return agents.stream()
                .filter(DeliveryAgent::isAvailable)
                .collect(Collectors.toList());
    }
    public int getRestaurantId() { return restaurantId; }

    public String getName() { return name; }

    public String getLocation() { return location; }

    public String getCuisineType() { return cuisineType; }

    public boolean isOpen() { return isOpen; }

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