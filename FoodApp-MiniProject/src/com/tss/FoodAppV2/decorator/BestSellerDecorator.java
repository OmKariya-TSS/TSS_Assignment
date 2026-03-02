package com.tss.FoodAppV2.decorator;

import com.tss.FoodAppV2.model.MenuItem;

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