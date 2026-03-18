package com.tss.FoodApp.ui;


import com.tss.FoodApp.model.MenuItem;
import com.tss.FoodApp.service.interfaces.IMenuService;
import com.tss.FoodApp.singleton.RestaurantRegistry;

import java.util.List;

public class BrowsePanel {

    private final RestaurantRegistry registry;
    private final IMenuService menuService;

    public BrowsePanel(RestaurantRegistry registry, IMenuService menuService) {
        this.registry    = registry;
        this.menuService = menuService;
    }

    public void browseRestaurants() {
        registry.displayAll();
    }

    public void viewMenuForRestaurant(int restaurantId) {
        System.out.println("\n--- 🍽 Menu ---");
        List<MenuItem> menu = menuService.getMenu(restaurantId);
        if (menu.isEmpty()) {
            System.out.println("⚠ No items in menu yet.");
            return;
        }
        System.out.println("📋 Menu for Restaurant ID " + restaurantId + ":");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("  %-4s | %-20s | %-10s | %-10s | %-15s | %-25s | %-15s%n",
                "🆔", "🍔 Name", "💰 Price", "📦 Available", "📂 Category", "📝 Description", "🏷 Tags");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

        menu.forEach(item -> {
            String tags = "";
            if (item.isBestSeller()) tags += "⭐BestSeller";
            if (item.isSpicy())      tags += "🌶Spicy";
            if (item.isNew())        tags += "🆕Newly added";

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
}