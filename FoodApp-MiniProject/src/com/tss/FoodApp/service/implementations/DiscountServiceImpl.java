package com.tss.FoodApp.service.implementations;

import com.tss.FoodApp.discount.DiscountContext;
import com.tss.FoodApp.discount.FlatDiscountStrategy;
import com.tss.FoodApp.discount.NoDiscountStrategy;
import com.tss.FoodApp.discount.PercentageDiscountStrategy;
import com.tss.FoodApp.exceptions.InvalidDiscountException;
import com.tss.FoodApp.service.interfaces.IDiscountService;
import com.tss.FoodApp.singleton.AppConfig;

public class DiscountServiceImpl implements IDiscountService {

    private final DiscountContext discountContext;
    private final AppConfig config;

    public DiscountServiceImpl(DiscountContext discountContext) {
        if (discountContext == null) {
            throw new IllegalArgumentException("DiscountContext cannot be null");
        }
        this.discountContext = discountContext;
        this.config = AppConfig.getInstance();
    }

    @Override
    public void setFlatDiscount(double amount, double threshold) {

        if (amount <= 0) {
            throw new InvalidDiscountException("Flat discount amount must be greater than 0");
        }

        if (threshold < 0) {
            throw new InvalidDiscountException("Threshold cannot be negative");
        }

        config.setFlatDiscountAmount(amount);
        config.setDiscountThreshold(threshold);
        config.setActiveDiscountType("FLAT");

        discountContext.setStrategy(
                new FlatDiscountStrategy(amount, threshold)
        );

        System.out.println("✅ Flat discount set: ₹" + amount
                + " off on orders above ₹" + threshold);
    }

    @Override
    public void setPercentageDiscount(double percentage, double threshold) {

        if (percentage <= 0 || percentage > 100) {
            throw new InvalidDiscountException(
                    "Percentage must be between 1 and 100"
            );
        }

        if (threshold < 0) {
            throw new InvalidDiscountException("Threshold cannot be negative");
        }

        config.setDiscountPercentage(percentage);
        config.setDiscountThreshold(threshold);
        config.setActiveDiscountType("PERCENTAGE");

        discountContext.setStrategy(
                new PercentageDiscountStrategy(percentage, threshold)
        );

        System.out.println("✅ Percentage discount set: " + percentage
                + "% off on orders above ₹" + threshold);
    }
    @Override
    public void setNoDiscount() {

        config.setActiveDiscountType("NONE");
        discountContext.setStrategy(new NoDiscountStrategy());

        System.out.println("✅ Discounts disabled.");
    }

    @Override
    public void updateThreshold(double threshold) {

        if (threshold < 0) {
            throw new InvalidDiscountException("Threshold cannot be negative");
        }

        config.setDiscountThreshold(threshold);
        String type = config.getActiveDiscountType();

        switch (type) {

            case "FLAT" -> discountContext.setStrategy(
                    new FlatDiscountStrategy(
                            config.getFlatDiscountAmount(),
                            threshold
                    )
            );

            case "PERCENTAGE" -> discountContext.setStrategy(
                    new PercentageDiscountStrategy(
                            config.getDiscountPercentage(),
                            threshold
                    )
            );

            case "NONE" -> {
                new NoDiscountStrategy();
            }

            default -> throw new InvalidDiscountException(
                    "Unknown discount type configured: " + type
            );
        }

        System.out.println("✅ Threshold updated to ₹" + threshold);
    }

    @Override
    public void showCurrentConfig() {
        config.displayDiscountConfig();
        System.out.println("  Live Strategy: "
                + discountContext.getDiscountInfo());
    }

    @Override
    public DiscountContext getDiscountContext() {
        return discountContext;
    }
}