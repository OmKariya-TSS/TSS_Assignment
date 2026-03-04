package com.tss.FoodAppV4.observer;

import com.tss.FoodAppV4.enums.OrderStatus;
import com.tss.FoodAppV4.model.Order;

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