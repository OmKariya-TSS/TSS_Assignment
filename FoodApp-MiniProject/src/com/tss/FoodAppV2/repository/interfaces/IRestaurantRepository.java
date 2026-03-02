package com.tss.FoodAppV2.repository.interfaces;

import com.tss.FoodAppV2.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantRepository {

    void save(Restaurant restaurant);

    Optional<Restaurant> findById(int id);

    List<Restaurant> findAll();

    void update(Restaurant restaurant);

    void delete(int id);
}