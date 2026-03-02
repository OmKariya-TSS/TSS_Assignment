package com.tss.FoodAppV2.observer;

import com.tss.FoodAppV2.enums.OrderStatus;
import com.tss.FoodAppV2.model.Order;

public interface OrderObserver {

    void update(Order order, OrderStatus newStatus);
}