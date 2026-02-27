package com.tss.FoodApp.discount;


public interface DiscountStrategy {

    double calculate(double subtotal);

    boolean isApplicable(double subtotal);

    String getDescription();
}