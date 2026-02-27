package com.tss.FoodApp.decorator;

import com.tss.FoodApp.model.MenuItem;

public class BestSellerDecorator extends MenuItemDecorator {

    public BestSellerDecorator(MenuItem item) {
        super(item);
    }

    @Override
    public void display() {
        wrappedItem.display();
        System.out.println("   BESTSELLER");
    }
}