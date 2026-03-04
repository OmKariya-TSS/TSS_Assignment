package com.tss.FoodAppV4.observer;

import com.tss.FoodAppV4.enums.OrderStatus;
import com.tss.FoodAppV4.model.Order;

public interface OrderObserver {

    void update(Order order, OrderStatus newStatus);
}