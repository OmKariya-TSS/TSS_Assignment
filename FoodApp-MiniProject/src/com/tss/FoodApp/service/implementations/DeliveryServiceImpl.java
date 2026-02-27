package com.tss.FoodApp.service.implementations;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.exceptions.AgentNotAvailableException;
import com.tss.FoodApp.exceptions.InvalidOrderStateException;
import com.tss.FoodApp.exceptions.RestaurantNotFoundException;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.model.Restaurant;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.service.interfaces.IDeliveryService;

import java.util.List;
import java.util.Random;

public class DeliveryServiceImpl implements IDeliveryService {

    private final IRestaurantRepository restaurantRepo;
    private final Random random = new Random();

    public DeliveryServiceImpl(IRestaurantRepository restaurantRepo) {
        if (restaurantRepo == null) {
            throw new IllegalArgumentException("Restaurant repository cannot be null");
        }
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public DeliveryAgent assignAgent(Order order) {

        if (order == null || order.getRestaurant() == null) {
            throw new IllegalArgumentException("Order or Restaurant cannot be null");
        }

        if (order.getAssignedAgent() != null) {
            throw new InvalidOrderStateException(
                    "Order #" + order.getOrderId() + " already has an assigned agent."
            );
        }

        int restaurantId = order.getRestaurant().getRestaurantId();

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Restaurant not found for order #" + order.getOrderId())
                );

        List<DeliveryAgent> availableAgents = restaurant.getAvailableAgents();

        if (availableAgents == null || availableAgents.isEmpty()) {
            throw new AgentNotAvailableException(
                    "No delivery agents available for restaurant: " + restaurant.getName()
            );
        }

        DeliveryAgent agent = availableAgents.get(random.nextInt(availableAgents.size()));

        agent.markBusy();
        order.assignAgent(agent);

        System.out.println("🚴 Assigned Agent: " + agent.getName()
                + " for order #" + order.getOrderId());

        return agent;
    }

    @Override
    public void markDelivered(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (order.getAssignedAgent() == null) {
            throw new InvalidOrderStateException(
                    "Order #" + order.getOrderId() + " has no assigned agent."
            );
        }

        if (order.getStatus()== OrderStatus.DELIVERED) {
            throw new InvalidOrderStateException(
                    "Order #" + order.getOrderId() + " is already delivered."
            );
        }

        order.getAssignedAgent().markAvailable();
        order.setStatus(OrderStatus.DELIVERED);

        System.out.println("📦 Order #" + order.getOrderId() + " marked as delivered.");
    }

    @Override
    public void addAgent(int restaurantId, DeliveryAgent agent) {

        if (agent == null) {
            throw new IllegalArgumentException("Delivery agent cannot be null");
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found to add agent"
                        )
                );

        restaurant.addAgent(agent);
    }

    @Override
    public void removeAgent(int agentId) {

        if (agentId <= 0) {
            throw new IllegalArgumentException("Invalid agent ID");
        }

        restaurantRepo.findAll().forEach(restaurant ->
                restaurant.removeAgent(agentId)
        );
    }

    @Override
    public List<DeliveryAgent> getAvailableAgents(int restaurantId) {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }

        return restaurantRepo.findById(restaurantId)
                .map(Restaurant::getAvailableAgents)
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Restaurant not found")
                );
    }
}