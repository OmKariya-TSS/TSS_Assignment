package com.tss.FoodApp.service.interfaces;

import com.tss.FoodApp.discount.DiscountContext;

public interface IDiscountService {
    void setFlatDiscount(double amount, double threshold);
    void setPercentageDiscount(double percentage, double threshold);
    void setNoDiscount();
    void updateThreshold(double threshold);
    void showCurrentConfig();
    DiscountContext getDiscountContext();
}