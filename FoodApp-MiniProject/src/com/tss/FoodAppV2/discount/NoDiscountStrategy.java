package com.tss.FoodAppV2.discount;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculate(double subtotal) {
        return 0;
    }
    @Override
    public boolean isApplicable(double subtotal) {
        return false;
    }
    @Override
    public String getDescription() {
        return "No discount applied";
    }
}