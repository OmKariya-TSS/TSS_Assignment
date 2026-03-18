package com.tss.FoodApp.repository.interfaces;

import com.tss.FoodApp.model.MenuItem;

import java.util.List;
import java.util.Optional;

public interface IMenuItemRepository {

    MenuItem save(MenuItem item,int restaurantId);

    Optional<MenuItem> findById(int id);

    List<MenuItem> findByRestaurant(int restaurantId);

    List<MenuItem> findAll();

    void update(MenuItem item);

    void delete(int id);
}