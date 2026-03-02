package com.tss.FoodAppV2.discount;

public class FlatDiscountStrategy implements DiscountStrategy {

    private double flatAmount;
    private double minimumOrderValue;

    public FlatDiscountStrategy(double flatAmount, double minimumOrderValue) {
        this.flatAmount = flatAmount;
        this.minimumOrderValue = minimumOrderValue;
    }

    @Override
    public double calculate(double subtotal) {
        return isApplicable(subtotal) ? flatAmount : 0;
    }

    @Override
    public boolean isApplicable(double subtotal) {
        return subtotal >= minimumOrderValue;
    }

    @Override
    public String getDescription() {
        return "Flat ₹" + flatAmount + " off on orders above ₹" + minimumOrderValue;
    }
}