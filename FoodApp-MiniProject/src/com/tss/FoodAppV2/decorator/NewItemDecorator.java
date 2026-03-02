package com.tss.FoodAppV2.decorator;

import com.tss.FoodAppV2.model.MenuItem;

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