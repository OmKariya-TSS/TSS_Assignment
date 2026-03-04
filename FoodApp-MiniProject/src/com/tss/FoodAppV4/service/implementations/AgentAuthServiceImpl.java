package com.tss.FoodAppV4.service.implementations;

import com.tss.FoodAppV4.exceptions.ValidationException;
import com.tss.FoodAppV4.model.DeliveryAgent;
import com.tss.FoodAppV4.repository.interfaces.IRestaurantRepository;
import com.tss.FoodAppV4.service.interfaces.IAgentAuthService;

public class AgentAuthServiceImpl implements IAgentAuthService {

    private final IRestaurantRepository restaurantRepo;
    private int currentAgentId = -1;
    public AgentAuthServiceImpl(IRestaurantRepository restaurantRepo) {
        if (restaurantRepo == null)
            throw new IllegalArgumentException("Restaurant repository cannot be null");
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public DeliveryAgent login(String phone, String password) {

        if (phone == null || phone.isBlank())
            throw new ValidationException("Phone cannot be empty.");

        if (password == null || password.isBlank())
            throw new ValidationException("Password cannot be empty.");

        DeliveryAgent agent = restaurantRepo.findAll()
                .stream()
                .flatMap(r -> r.getAgents().stream())
                .filter(a -> a.getPhone().equals(phone)
                        && a.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() ->
                        new ValidationException("Invalid phone or password."));

        currentAgentId = agent.getAgentId();

        System.out.println("✅ Agent logged in: " + agent.getName()
                + " (ID: " + agent.getAgentId() + ")");
        return agent;
    }

    @Override
    public void logout() {
        if (currentAgentId != -1) {
            getFreshAgent().ifPresent(a ->
                    System.out.println("ℹ Logged out: " + a.getName()));
        }
        currentAgentId = -1;
    }

    @Override
    public DeliveryAgent getCurrentAgent() {
        return getFreshAgent().orElse(null);
    }

    @Override
    public boolean isLoggedIn() {
        return currentAgentId != -1;
    }

    private java.util.Optional<DeliveryAgent> getFreshAgent() {
        if (currentAgentId == -1)
            return java.util.Optional.empty();

        return restaurantRepo.findAll().stream()
                .flatMap(r -> r.getAgents().stream())
                .filter(a -> a.getAgentId() == currentAgentId)
                .findFirst();
    }
}