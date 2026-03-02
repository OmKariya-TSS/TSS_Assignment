package com.tss.FoodAppV3.observer;

import com.tss.FoodAppV3.enums.OrderStatus;
import com.tss.FoodAppV3.model.Order;

public interface OrderSubject {

    void registerObserver(OrderObserver observer);

    void removeObserver(OrderObserver observer);

    void notifyObservers(Order order, OrderStatus newStatus);
}