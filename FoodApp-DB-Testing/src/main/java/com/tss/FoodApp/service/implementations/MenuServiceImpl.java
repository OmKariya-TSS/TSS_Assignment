package com.tss.FoodApp.service.implementations;

import com.tss.FoodApp.exceptions.InvalidMenuItemException;
import com.tss.FoodApp.exceptions.RestaurantNotFoundException;
import com.tss.FoodApp.model.MenuItem;
import com.tss.FoodApp.model.Restaurant;
import com.tss.FoodApp.repository.interfaces.IMenuItemRepository;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.service.interfaces.IMenuService;

import java.util.List;

public class MenuServiceImpl implements IMenuService {

    private final IRestaurantRepository restaurantRepo;
    private final IMenuItemRepository menuItemRepo;

    public MenuServiceImpl(IRestaurantRepository restaurantRepo, IMenuItemRepository menuItemRepo) {
        if (restaurantRepo == null || menuItemRepo == null) {
            throw new IllegalArgumentException("Repositories cannot be null");
        }
        this.restaurantRepo = restaurantRepo;
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public void addItem(int restaurantId, MenuItem item) {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }
        if (item == null) {
            throw new InvalidMenuItemException("Menu item cannot be null");
        }

        restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Restaurant not found with ID: " + restaurantId));

        MenuItem saved = menuItemRepo.save(item, restaurantId);

        System.out.println("✅ Menu item added: " + saved.getName());
    }

    @Override
    public void removeItem(int restaurantId, int itemId) {

        if (restaurantId <= 0 || itemId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID or item ID");
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Restaurant not found with ID: " + restaurantId)
                );

        menuItemRepo.delete(itemId);

        restaurant.removeMenuItem(itemId);
        restaurantRepo.update(restaurant);

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
                        new RestaurantNotFoundException("Restaurant not found with ID: " + restaurantId)
                );

        MenuItem item = menuItemRepo.findById(itemId)
                .orElseThrow(() -> new InvalidMenuItemException("Menu item not found with ID: " + itemId));

        item.setPrice(price);

        menuItemRepo.update(item);

        restaurant.updateMenuItemPrice(itemId, price);
        restaurantRepo.update(restaurant);

        System.out.println("✅ Menu item price updated (ID: " + itemId + ") to ₹" + price);
    }

    @Override
    public List<MenuItem> getMenu(int restaurantId) {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }

        return menuItemRepo.findByRestaurant(restaurantId);
    }


    @Override
    public void displayMenu(int restaurantId) {

        List<MenuItem> menu = getMenu(restaurantId);

        if (menu.isEmpty()) {
            System.out.println("⚠ No menu items found for this restaurant.");
            return;
        }

        System.out.println("📋 Menu for Restaurant ID " + restaurantId + ":");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("  %-4s | %-20s | %-10s | %-10s | %-15s | %-25s | %-15s%n",
                "🆔", "🍔 Name", "💰 Price", "📦 Available", "📂 Category", "📝 Description", "🏷 Tags");
        menu.forEach(item -> {
            String tags = "";
            if (item.isBestSeller()) tags += "⭐";
            if (item.isSpicy())      tags += "🌶";
            if (item.isNew())        tags += "🆕";

            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            System.out.printf("  %-4d | %-20s | ₹%-8.2f | %-10s | %-15s | %-25s | %-15s%n",
                    item.getItemId(),
                    item.getName(),
                    item.getPrice(),
                    item.isAvailable() ? "✅ Yes" : "❌ No",
                    item.getCategory(),
                    item.getDescription(),
                    tags.isEmpty() ? "➖" : tags
            );
        });
    }


    @Override
    public void updateItemTags(int restaurantId, MenuItem item) {
        if (item == null || item.getItemId() <= 0) {
            throw new InvalidMenuItemException("Invalid menu item.");
        }
        menuItemRepo.update(item);
    }
}