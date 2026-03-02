package com.tss.FoodAppV2.service.interfaces;

import com.tss.FoodAppV2.discount.DiscountContext;

public interface IDiscountService {
    void setFlatDiscount(double amount, double threshold);
    void setPercentageDiscount(double percentage, double threshold);
    void setNoDiscount();
    void updateThreshold(double threshold);
    void showCurrentConfig();
    DiscountContext getDiscountContext();
}