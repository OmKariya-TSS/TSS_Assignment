package com.tss.FoodAppV3.singleton;

import com.tss.FoodAppV3.exceptions.RestaurantRegistryException;
import com.tss.FoodAppV3.model.Restaurant;
import com.tss.FoodAppV3.repository.interfaces.IRestaurantRepository;

import java.util.List;
import java.util.Optional;

public class RestaurantRegistry {

    private static RestaurantRegistry instance;
    private final IRestaurantRepository repository;

    private RestaurantRegistry(IRestaurantRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.repository = repository;
    }

    public static synchronized RestaurantRegistry getInstance(
            IRestaurantRepository repository) {

        if (instance == null) {
            if (repository == null) {
                throw new RestaurantRegistryException(
                        "Repository required for first initialization"
                );
            }
            instance = new RestaurantRegistry(repository);
        }

        return instance;
    }
   public static synchronized void resetInstance() {
        instance = null;
    }

    public void registerRestaurant(Restaurant restaurant) {

        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant cannot be null");
        }

        repository.save(restaurant);
    }

    public void removeRestaurant(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }

        if (repository.findById(id).isEmpty()) {
            throw new RestaurantRegistryException(
                    "Restaurant not found with ID: " + id
            );
        }

        repository.delete(id);
    }

    public Optional<Restaurant> getById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }

        return repository.findById(id);
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public void displayAll() {

        List<Restaurant> restaurants = repository.findAll();

        if (restaurants.isEmpty()) {
            throw new RestaurantRegistryException(
                    "No restaurants registered."
            );
        }

        System.out.println("\n===== REGISTERED RESTAURANTS =====");
        restaurants.forEach(System.out::println);
        System.out.println("==================================\n");
    }
}