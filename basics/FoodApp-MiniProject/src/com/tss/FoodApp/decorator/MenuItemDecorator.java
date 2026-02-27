package com.tss.FoodApp.decorator;

import com.tss.FoodApp.model.MenuItem;

public abstract class MenuItemDecorator extends MenuItem {

    protected MenuItem wrappedItem;

    public MenuItemDecorator(MenuItem item) {
        super(item.getItemId(), item.getName(), item.getPrice(),item.getCategory(), item.getDescription());
        this.wrappedItem = item;
    }

    @Override
    public void display() {
        wrappedItem.display();
    }

    @Override
    public String getName() {
        return wrappedItem.getName();
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice();
    }

    @Override
    public int getItemId() {
        return wrappedItem.getItemId();
    }
}