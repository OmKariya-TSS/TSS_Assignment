package com.tss.FoodAppV2.service.interfaces;

import com.tss.FoodAppV2.model.MenuItem;

import java.util.List;

public interface IMenuService {


    void addItem(int restaurantId, MenuItem item);


    void removeItem(int restaurantId, int itemId);


    void updateItemPrice(int restaurantId, int itemId, double price);


    List<MenuItem> getMenu(int restaurantId);


    void displayMenu(int restaurantId);
}