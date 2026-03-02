package com.tss.FoodAppV2.discount;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private double percentage;
    private double minimumOrderValue;
    public PercentageDiscountStrategy(double percentage, double minimumOrderValue) {
        this.percentage = percentage;
        this.minimumOrderValue = minimumOrderValue;
    }
    @Override
    public double calculate(double subtotal) {
        return isApplicable(subtotal)
                ? (subtotal * percentage / 100)
                : 0;
    }
    @Override
    public boolean isApplicable(double subtotal) {
        return subtotal >= minimumOrderValue;
    }
    @Override
    public String getDescription() {
        return percentage + "% off on orders above ₹" + minimumOrderValue;
    }
}