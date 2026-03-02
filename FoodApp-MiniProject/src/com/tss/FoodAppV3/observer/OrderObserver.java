package com.tss.FoodAppV3.observer;

import com.tss.FoodAppV3.enums.OrderStatus;
import com.tss.FoodAppV3.model.Order;

public interface OrderObserver {

    void update(Order order, OrderStatus newStatus);
}