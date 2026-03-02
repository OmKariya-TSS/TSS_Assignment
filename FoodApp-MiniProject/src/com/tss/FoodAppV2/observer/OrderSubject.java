package com.tss.FoodAppV2.observer;

import com.tss.FoodAppV2.enums.OrderStatus;
import com.tss.FoodAppV2.model.Order;

public interface OrderSubject {

    void registerObserver(OrderObserver observer);

    void removeObserver(OrderObserver observer);

    void notifyObservers(Order order, OrderStatus newStatus);
}