package com.tss.FoodAppV2.service.implementations;

import com.tss.FoodAppV2.exceptions.InvalidMenuItemException;
import com.tss.FoodAppV2.exceptions.RestaurantNotFoundException;
import com.tss.FoodAppV2.model.MenuItem;
import com.tss.FoodAppV2.model.Restaurant;
import com.tss.FoodAppV2.repository.interfaces.IRestaurantRepository;
import com.tss.FoodAppV2.service.interfaces.IMenuService;

import java.util.List;

public class MenuServiceImpl implements IMenuService {

    private final IRestaurantRepository restaurantRepo;

    public MenuServiceImpl(IRestaurantRepository restaurantRepo) {
        if (restaurantRepo == null) {
            throw new IllegalArgumentException("Restaurant repository cannot be null");
        }
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public void addItem(int restaurantId, MenuItem item) {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }

        if (item == null) {
            throw new InvalidMenuItemException("Menu item cannot be null");
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found with ID: " + restaurantId
                        )
                );

        restaurant.addMenuItem(item);

        System.out.println("✅ Menu item added: " + item.getName());
    }

    @Override
    public void removeItem(int restaurantId, int itemId) {

        if (restaurantId <= 0 || itemId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID or item ID");
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found with ID: " + restaurantId
                        )
                );

        restaurant.removeMenuItem(itemId);

        System.out.println("✅ Menu item removed (ID: " + itemId + ")");
    }

    @Override
    public void updateItemPrice(int restaurantId, int itemId, double price) {

        if (restaurantId <= 0 || itemId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID or item ID");
        }

        if (price <= 0) {
            throw new InvalidMenuItemException("Price must be greater than 0");
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found with ID: " + restaurantId
                        )
                );

        restaurant.updateMenuItemPrice(itemId, price);


        System.out.println("✅ Menu item price updated (ID: "
                + itemId + ") to ₹" + price);
    }

    @Override
    public List<MenuItem> getMenu(int restaurantId) {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found with ID: " + restaurantId
                        )
                );

        return restaurant.getMenu();
    }

    @Override
    public void displayMenu(int restaurantId) {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found with ID: " + restaurantId
                        )
                );

        System.out.println("📋 Menu for " + restaurant.getName() + ":");
        restaurant.displayMenu();
    }
}