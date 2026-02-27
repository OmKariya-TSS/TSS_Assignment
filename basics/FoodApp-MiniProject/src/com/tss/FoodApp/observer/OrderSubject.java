package com.tss.FoodApp.observer;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.model.Order;

public interface OrderSubject {

    void registerObserver(OrderObserver observer);

    void removeObserver(OrderObserver observer);

    void notifyObservers(Order order, OrderStatus newStatus);
}