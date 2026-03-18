package com.tss.FoodApp.service.interfaces;

import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.Order;

import java.util.List;

public interface IDeliveryService {


    DeliveryAgent assignAgent(Order order);

    void markDelivered(Order order);


    void addAgent(int restaurantId, DeliveryAgent agent);


    void removeAgent(int agentId);


    List<DeliveryAgent> getAvailableAgents(int restaurantId);
}