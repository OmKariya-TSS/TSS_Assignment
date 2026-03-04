package com.tss.FoodAppV4.observer;

import com.tss.FoodAppV4.enums.OrderStatus;
import com.tss.FoodAppV4.model.Order;

public interface OrderSubject {

    void registerObserver(OrderObserver observer);

    void removeObserver(OrderObserver observer);

    void notifyObservers(Order order, OrderStatus newStatus);
}