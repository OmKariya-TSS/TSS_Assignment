package com.tss.FoodAppV2.decorator;

import com.tss.FoodAppV2.model.MenuItem;

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