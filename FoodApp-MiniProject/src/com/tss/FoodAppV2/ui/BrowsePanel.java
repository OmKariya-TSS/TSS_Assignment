package com.tss.FoodAppV2.ui;


import com.tss.FoodAppV2.model.MenuItem;
import com.tss.FoodAppV2.service.interfaces.IMenuService;
import com.tss.FoodAppV2.singleton.RestaurantRegistry;

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
        menu.forEach(MenuItem::display);
    }
}