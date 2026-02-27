package com.tss.FoodApp.decorator;

import com.tss.FoodApp.model.MenuItem;

public class NewItemDecorator extends MenuItemDecorator {
    public NewItemDecorator(MenuItem item) {
        super(item);
    }
    @Override
    public void display() {
        wrappedItem.display();
        System.out.println("   NEW ITEM");
    }
}