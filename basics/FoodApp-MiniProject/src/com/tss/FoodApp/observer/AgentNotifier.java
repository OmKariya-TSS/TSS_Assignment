package com.tss.FoodApp.observer;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.Order;

public class AgentNotifier implements OrderObserver {

    @Override
    public void update(Order order, OrderStatus newStatus) {

        DeliveryAgent agent = order.getAssignedAgent();

        if (agent == null) {
            return;
        }

        System.out.println(
                "Agent " + agent.getName()
                        + " — order #" + order.getOrderId()
                        + " status: " + newStatus
        );

        if (newStatus == OrderStatus.OUT_FOR_DELIVERY) {
            System.out.println(
                    "Pickup from: " + order.getRestaurant().getName()
                            + " and deliver to: "
                            + order.getCustomer().getName()
            );
        }
    }
}