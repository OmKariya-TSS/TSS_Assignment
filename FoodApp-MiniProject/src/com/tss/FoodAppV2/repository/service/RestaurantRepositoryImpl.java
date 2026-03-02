package com.tss.FoodAppV2.repository.service;

import com.tss.FoodAppV2.exceptions.ValidationException;
import com.tss.FoodAppV2.model.Restaurant;
import com.tss.FoodAppV2.repository.interfaces.IRestaurantRepository;

import java.util.*;

public class RestaurantRepositoryImpl implements IRestaurantRepository {

    private final Map<Integer, Restaurant> store = new HashMap<>();
    private int idCounter = 1;

    @Override
    public void save(Restaurant restaurant) {

        if (restaurant == null) {
            throw new ValidationException("Restaurant cannot be null.");
        }

        if (restaurant.getRestaurantId() < 0) {
            throw new ValidationException("Restaurant ID cannot be negative.");
        }

        if (restaurant.getRestaurantId() == 0) {
            restaurant.setRestaurantId(idCounter++);
        } else {
            if (restaurant.getRestaurantId() >= idCounter) {
                idCounter = restaurant.getRestaurantId() + 1;
            }
        }

        store.put(restaurant.getRestaurantId(), restaurant);
    }

    @Override
    public Optional<Restaurant> findById(int id) {

        if (id <= 0) {
            throw new ValidationException("Restaurant ID must be greater than zero.");
        }

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Restaurant> findAll() {
        return Collections.unmodifiableList(
                new ArrayList<>(store.values())
        );
    }

    @Override
    public void update(Restaurant restaurant) {

        if (restaurant == null) {
            throw new ValidationException("Restaurant cannot be null.");
        }

        if (restaurant.getRestaurantId() <= 0) {
            throw new ValidationException("Invalid Restaurant ID.");
        }

        if (!store.containsKey(restaurant.getRestaurantId())) {
            throw new ValidationException("Restaurant does not exist.");
        }

        store.put(restaurant.getRestaurantId(), restaurant);
    }

    @Override
    public void delete(int id) {

        if (id <= 0) {
            throw new ValidationException("Invalid Restaurant ID.");
        }

        if (!store.containsKey(id)) {
            throw new ValidationException("Restaurant not found.");
        }

        store.remove(id);
    }
}