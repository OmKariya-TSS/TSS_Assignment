package com.tss.FoodAppV4.observer;

import com.tss.FoodAppV4.enums.OrderStatus;
import com.tss.FoodAppV4.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderEventManager implements OrderSubject {

    private List<OrderObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(OrderObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(OrderObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Order order, OrderStatus status) {
        observers.forEach(o -> o.update(order, status));
    }
}