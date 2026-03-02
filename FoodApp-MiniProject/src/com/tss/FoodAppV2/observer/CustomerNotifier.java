package com.tss.FoodAppV2.observer;

import com.tss.FoodAppV2.enums.OrderStatus;
import com.tss.FoodAppV2.model.Order;

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