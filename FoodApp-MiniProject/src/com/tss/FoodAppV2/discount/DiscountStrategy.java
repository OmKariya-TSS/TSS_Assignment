package com.tss.FoodAppV2.discount;


public interface DiscountStrategy {

    double calculate(double subtotal);

    boolean isApplicable(double subtotal);

    String getDescription();
}