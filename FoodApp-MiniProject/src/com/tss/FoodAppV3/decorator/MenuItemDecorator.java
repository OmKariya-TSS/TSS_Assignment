package com.tss.FoodAppV3.decorator;

import com.tss.FoodAppV3.model.MenuItem;

import java.io.Serializable;

public abstract class MenuItemDecorator extends MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
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