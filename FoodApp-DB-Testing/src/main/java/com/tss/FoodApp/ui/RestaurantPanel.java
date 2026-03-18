package com.tss.FoodApp.ui;


import com.tss.FoodApp.exceptions.RestaurantNotFoundException;
import com.tss.FoodApp.exceptions.RestaurantRegistryException;
import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.Restaurant;
import com.tss.FoodApp.singleton.RestaurantRegistry;

public class RestaurantPanel {

    private final RestaurantRegistry registry;
    private final InputHelper input;

    static int restaurantCnt = 1;

    public RestaurantPanel(RestaurantRegistry registry, InputHelper input) {
        this.registry = registry;
        this.input    = input;
    }

    public void addRestaurant() {
        try {
            System.out.println("\n--- ➕ Add Restaurant ---");
            System.out.print("  Name         : ");
            String name = input.getScanner().nextLine().trim();
            System.out.print("  Location     : ");
            String location = input.getScanner().nextLine().trim();
            System.out.print("  Cuisine Type : ");
            String cuisine = input.getScanner().nextLine().trim();

            if (name.isEmpty() || location.isEmpty() || cuisine.isEmpty()) {
                throw new ValidationException("Name, location and cuisine cannot be empty.");
            }

            registry.registerRestaurant(new Restaurant(restaurantCnt++, name, location, cuisine));
            System.out.println("✅ Restaurant '" + name + "' added!");

        } catch (ValidationException e) {
            System.out.println("  ❌ Validation error: " + e.getMessage());
        } catch (RestaurantRegistryException e) {
            System.out.println("  ❌ Registry error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    public void removeRestaurant() {
        try {
            int id = input.readInt("  Enter restaurant ID to remove: ");

            registry.getById(id).ifPresentOrElse(r -> {
                registry.removeRestaurant(id);
                System.out.println("✅ Restaurant '" + r.getName() + "' removed.");
            }, () -> {
                throw new RestaurantNotFoundException("Restaurant not found with ID: " + id);
            });

        } catch (RestaurantNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (RestaurantRegistryException e) {
            System.out.println("  ❌ Registry error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    public void viewAllRestaurants() {
        try {
            registry.displayAll();
        } catch (RestaurantRegistryException e) {
            System.out.println("  ❌ Registry error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }
}