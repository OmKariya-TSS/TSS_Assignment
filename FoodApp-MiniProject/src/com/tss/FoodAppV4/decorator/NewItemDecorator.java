package com.tss.FoodAppV4.decorator;

import com.tss.FoodAppV4.model.MenuItem;

import java.io.Serializable;

public class NewItemDecorator extends MenuItemDecorator implements Serializable {
    private static final long serialVersionUID = 1L;
    public NewItemDecorator(MenuItem item) {
        super(item);
    }
    @Override
    public void display() {
        wrappedItem.display();
        System.out.println("   NEW ITEM");
    }
}