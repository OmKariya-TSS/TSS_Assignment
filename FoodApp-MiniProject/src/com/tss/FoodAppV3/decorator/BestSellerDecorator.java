package com.tss.FoodAppV3.decorator;

import com.tss.FoodAppV3.model.MenuItem;

import java.io.Serializable;

public class BestSellerDecorator extends MenuItemDecorator implements Serializable {
    private static final long serialVersionUID = 1L;
    public BestSellerDecorator(MenuItem item) {
        super(item);
    }

    @Override
    public void display() {
        wrappedItem.display();
        System.out.println("   BESTSELLER");
    }
}