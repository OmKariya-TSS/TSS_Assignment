package com.tss.FoodApp.service.implementations;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.exceptions.AgentNotAvailableException;
import com.tss.FoodApp.exceptions.InvalidOrderStateException;
import com.tss.FoodApp.exceptions.RestaurantNotFoundException;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.model.Restaurant;
import com.tss.FoodApp.repository.interfaces.IDeliveryAgentRepository;
import com.tss.FoodApp.repository.interfaces.IOrderRepository;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.service.interfaces.IDeliveryService;

import java.util.List;
import java.util.Random;

public class DeliveryServiceImpl implements IDeliveryService {

    private final IRestaurantRepository restaurantRepo;
    private final IDeliveryAgentRepository agentRepo;
    private final IOrderRepository orderRepo;
    private final Random random = new Random();

    public DeliveryServiceImpl(IRestaurantRepository restaurantRepo, IDeliveryAgentRepository agentRepo,IOrderRepository orderRepo) {
        if (restaurantRepo == null || agentRepo == null) {
            throw new IllegalArgumentException("Repositories cannot be null");
        }
        this.restaurantRepo = restaurantRepo;
        this.agentRepo = agentRepo;
        this.orderRepo = orderRepo;
    }
    @Override
    public DeliveryAgent assignAgent(Order order) {

        if (order == null || order.getRestaurant() == null) {
            throw new IllegalArgumentException("Order or Restaurant cannot be null");
        }

        if (order.getAssignedAgent() != null) {
            throw new InvalidOrderStateException(
                    "Order #" + order.getOrderId() + " already has an assigned agent.");
        }

        int restaurantId = order.getRestaurant().getRestaurantId();

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found for order #" + order.getOrderId()));

        List<DeliveryAgent> availableAgents = agentRepo.findByRestaurant(restaurantId)
                .stream()
                .filter(DeliveryAgent::isAvailable)
                .toList();

        if (availableAgents.isEmpty()) {
            throw new AgentNotAvailableException(
                    "No delivery agents available for restaurant: " + restaurant.getName());
        }

        DeliveryAgent agent = availableAgents.get(random.nextInt(availableAgents.size()));

        agent.markBusy();
        agentRepo.update(agent);

        order.assignAgent(agent);

        orderRepo.update(order);

        System.out.println("🚴 Assigned Agent: " + agent.getName()
                + " for Order #" + order.getOrderId());

        return agent;
    }

    @Override
    public void markDelivered(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        DeliveryAgent agent = order.getAssignedAgent();

        if (agent == null) {
            throw new InvalidOrderStateException(
                    "Order #" + order.getOrderId() + " has no assigned agent.");
        }

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new InvalidOrderStateException(
                    "Order #" + order.getOrderId() + " is already delivered.");
        }

        agent.markAvailable();
        agent.incrementDeliveries();
        agentRepo.update(agent);

        order.updateStatus(OrderStatus.DELIVERED);
        orderRepo.update(order);

        System.out.println("📦 Order #" + order.getOrderId() + " marked as delivered.");
    }

    @Override
    public void addAgent(int restaurantId, DeliveryAgent agent) {

        if (agent == null) {
            throw new IllegalArgumentException("Delivery agent cannot be null");
        }

        restaurantRepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Restaurant not found to add agent"));

        agent.setAvailable(true);
        agentRepo.save(agent, restaurantId);
    }

    @Override
    public void removeAgent(int agentId) {

        if (agentId <= 0) {
            throw new IllegalArgumentException("Invalid agent ID");
        }

        agentRepo.delete(agentId);
    }

    @Override
    public List<DeliveryAgent> getAvailableAgents(int restaurantId) {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }

        return agentRepo.findByRestaurant(restaurantId)
                .stream()
                .filter(DeliveryAgent::isAvailable)
                .toList();
    }

}