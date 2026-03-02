package com.tss.FoodAppV3.service.interfaces;

import com.tss.FoodAppV3.discount.DiscountContext;

public interface IDiscountService {
    void setFlatDiscount(double amount, double threshold);
    void setPercentageDiscount(double percentage, double threshold);
    void setNoDiscount();
    void updateThreshold(double threshold);
    void showCurrentConfig();
    DiscountContext getDiscountContext();
}