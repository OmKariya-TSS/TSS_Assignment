package com.tss.FoodAppV3.service.interfaces;

import com.tss.FoodAppV3.model.DeliveryAgent;
import com.tss.FoodAppV3.model.Order;

import java.util.List;

public interface IDeliveryService {


    DeliveryAgent assignAgent(Order order);

    void markDelivered(Order order);


    void addAgent(int restaurantId, DeliveryAgent agent);


    void removeAgent(int agentId);


    List<DeliveryAgent> getAvailableAgents(int restaurantId);
}