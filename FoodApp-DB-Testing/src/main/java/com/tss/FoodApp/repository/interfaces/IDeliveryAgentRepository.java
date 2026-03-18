package com.tss.FoodApp.repository.interfaces;


import com.tss.FoodApp.model.DeliveryAgent;

import java.util.List;
import java.util.Optional;

public interface IDeliveryAgentRepository {

    void save(DeliveryAgent agent, int restaurantId);

    Optional<DeliveryAgent> findById(int agentId);

    List<DeliveryAgent> findByRestaurant(int restaurantId);

    List<DeliveryAgent> findAll();

    void update(DeliveryAgent agent);

    void delete(int agentId);
}