package com.tss.FoodApp.observer;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.model.Order;

public interface OrderObserver {

    void update(Order order, OrderStatus newStatus);
}