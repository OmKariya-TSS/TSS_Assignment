package com.tss.FoodApp.observer;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.model.Order;

public class CustomerNotifier implements OrderObserver {

    @Override
    public void update(Order order, OrderStatus newStatus) {
        System.out.println(
                "Hey " + order.getCustomer().getName()
                        + ", your order #" + order.getOrderId()
                        + " is now: " + newStatus
        );
    }
}