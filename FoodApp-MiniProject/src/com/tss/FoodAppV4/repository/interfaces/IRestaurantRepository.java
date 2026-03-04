package com.tss.FoodAppV4.repository.interfaces;

import com.tss.FoodAppV4.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantRepository {

    void save(Restaurant restaurant);

    Optional<Restaurant> findById(int id);

    List<Restaurant> findAll();

    void update(Restaurant restaurant);

    void delete(int id);
}