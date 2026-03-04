package com.tss.FoodAppV4.repository.service;

import com.tss.FoodAppV4.exceptions.ValidationException;
import com.tss.FoodAppV4.model.Restaurant;
import com.tss.FoodAppV4.serialization.PersistenceManager;
import com.tss.FoodAppV4.repository.interfaces.IRestaurantRepository;

import java.util.*;

public class RestaurantRepositoryImpl implements IRestaurantRepository {
    private static final String FILE = "restaurants.dat";
    private Map<Integer, Restaurant> store = new HashMap<>();
    private int idCounter = 1;

    public RestaurantRepositoryImpl() {
        this.store = PersistenceManager.load(FILE, new HashMap<>());
        this.idCounter = store.keySet().stream()
                .mapToInt(Integer::intValue)
                .max().orElse(0) + 1;
    }
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
        PersistenceManager.save(FILE, store);

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
        PersistenceManager.save(FILE, store);

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
        PersistenceManager.save(FILE, store);
    }
}