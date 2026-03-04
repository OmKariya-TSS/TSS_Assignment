package com.tss.FoodAppV4.discount;

public class DiscountContext {
    private DiscountStrategy strategy;

    public DiscountContext(DiscountStrategy strategy) {
        this.strategy = strategy;
    }
    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }
    public double applyDiscount(double subtotal) {
        return strategy.calculate(subtotal);
    }
    public String getDiscountInfo() {
        return strategy.getDescription();
    }
    public boolean isDiscountApplicable(double subtotal) {
        return strategy.isApplicable(subtotal);
    }
}