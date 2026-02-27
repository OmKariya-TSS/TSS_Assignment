package com.tss.FoodApp.decorator;

import com.tss.FoodApp.model.MenuItem;

public class SpicyDecorator extends MenuItemDecorator {

    public SpicyDecorator(MenuItem item) {
        super(item);
    }

    @Override
    public void display() {
        wrappedItem.display();
        System.out.println("   SPICY");
    }
}