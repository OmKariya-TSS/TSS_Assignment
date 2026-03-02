package com.tss.FoodAppV3.service.implementations;

import com.tss.FoodAppV3.enums.OrderStatus;
import com.tss.FoodAppV3.exceptions.AgentNotAvailableException;
import com.tss.FoodAppV3.exceptions.InvalidOrderStateException;
import com.tss.FoodAppV3.exceptions.RestaurantNotFoundException;
import com.tss.FoodAppV3.model.DeliveryAgent;
import com.tss.FoodAppV3.model.Order;
import com.tss.FoodAppV3.model.Restaurant;
import com.tss.FoodAppV3.repository.interfaces.IRestaurantRepository;
import com.tss.FoodAppV3.service.interfaces.IDeliveryService;

import java.util.List;
import java.util.Random;

public class DeliveryServiceImpl implements IDeliveryService {

    private final IRestaurantRepository restaurantRepo;
    private final Random random = new Random();

    public DeliveryServiceImpl(IRestaurantRepository restaurantRepo) {
        if (restaurantRepo == null)
            throw new IllegalArgumentException("Restaurant repository cannot be null");
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public DeliveryAgent assignAgent(Order order) {

        if (order == null || order.getRestaurant() == null)
            throw new IllegalArgumentException("Order or Restaurant cannot be null");

        if (order.getAssignedAgent() != null)
            throw new InvalidOrderStateException(
                    "Order #" + order.getOrderId() + " already has an assigned agent.");

        int restaurantId = order.getRestaurant().getRestaurantId();

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found for order #" + order.getOrderId()));

        List<DeliveryAgent> availableAgents = restaurant.getAvailableAgents();

        if (availableAgents == null || availableAgents.isEmpty())
            throw new AgentNotAvailableException(
                    "No delivery agents available for restaurant: " + restaurant.getName());

        DeliveryAgent agent = availableAgents.get(random.nextInt(availableAgents.size()));

        agent.markBusy();
        order.assignAgent(agent);
        restaurantRepo.update(restaurant);

        System.out.println("🚴 Assigned Agent: " + agent.getName()
                + " for order #" + order.getOrderId());

        return agent;
    }

    @Override
    public void markDelivered(Order order) {

        if (order == null)
            throw new IllegalArgumentException("Order cannot be null");

        if (order.getAssignedAgent() == null)
            throw new InvalidOrderStateException(
                    "Order #" + order.getOrderId() + " has no assigned agent.");

        if (order.getStatus() == OrderStatus.DELIVERED)
            throw new InvalidOrderStateException(
                    "Order #" + order.getOrderId() + " is already delivered.");

        int agentId      = order.getAssignedAgent().getAgentId();
        int restaurantId = order.getRestaurant().getRestaurantId();

        restaurantRepo.findById(restaurantId).ifPresent(restaurant -> {
            restaurant.getAgents().stream()
                    .filter(a -> a.getAgentId() == agentId)
                    .findFirst()
                    .ifPresent(liveAgent -> {
                        liveAgent.markAvailable();
                        liveAgent.incrementDeliveries();
                    });
            restaurantRepo.update(restaurant);
        });

        order.getAssignedAgent().markAvailable();
        order.getAssignedAgent().incrementDeliveries();
        order.setStatus(OrderStatus.DELIVERED);

        System.out.println("📦 Order #" + order.getOrderId() + " marked as delivered.");
    }

    @Override
    public void addAgent(int restaurantId, DeliveryAgent agent) {

        if (agent == null)
            throw new IllegalArgumentException("Delivery agent cannot be null");

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found to add agent"));

        restaurant.addAgent(agent);
        restaurantRepo.update(restaurant);
    }

    @Override
    public void removeAgent(int agentId) {

        if (agentId <= 0)
            throw new IllegalArgumentException("Invalid agent ID");

        restaurantRepo.findAll().forEach(restaurant -> {
            boolean had = restaurant.getAgents().stream()
                    .anyMatch(a -> a.getAgentId() == agentId);
            if (had) {
                restaurant.removeAgent(agentId);
                restaurantRepo.update(restaurant);
            }
        });
    }

    @Override
    public List<DeliveryAgent> getAvailableAgents(int restaurantId) {

        if (restaurantId <= 0)
            throw new IllegalArgumentException("Invalid restaurant ID");

        return restaurantRepo.findById(restaurantId)
                .map(Restaurant::getAvailableAgents)
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Restaurant not found"));
    }
}