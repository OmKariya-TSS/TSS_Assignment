package com.tss.FoodAppV4.discount;


public interface DiscountStrategy {

    double calculate(double subtotal);

    boolean isApplicable(double subtotal);

    String getDescription();
}