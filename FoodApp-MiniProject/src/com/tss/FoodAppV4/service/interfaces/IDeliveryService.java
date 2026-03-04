package com.tss.FoodAppV4.service.interfaces;

import com.tss.FoodAppV4.model.DeliveryAgent;
import com.tss.FoodAppV4.model.Order;

import java.util.List;

public interface IDeliveryService {


    DeliveryAgent assignAgent(Order order);

    void markDelivered(Order order);


    void addAgent(int restaurantId, DeliveryAgent agent);


    void removeAgent(int agentId);


    List<DeliveryAgent> getAvailableAgents(int restaurantId);
}