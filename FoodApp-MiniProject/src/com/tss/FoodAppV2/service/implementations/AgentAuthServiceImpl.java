package com.tss.FoodAppV2.service.implementations;

import com.tss.FoodAppV2.exceptions.ValidationException;
import com.tss.FoodAppV2.model.DeliveryAgent;
import com.tss.FoodAppV2.repository.interfaces.IRestaurantRepository;
import com.tss.FoodAppV2.service.interfaces.IAgentAuthService;

public class AgentAuthServiceImpl implements IAgentAuthService {

    private final IRestaurantRepository restaurantRepo;
    private DeliveryAgent currentAgent = null;

    public AgentAuthServiceImpl(IRestaurantRepository restaurantRepo) {
        if (restaurantRepo == null) {
            throw new ValidationException("Restaurant repository cannot be null");
        }
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public DeliveryAgent login(String phone, String password) {

        if (phone == null || phone.trim().isEmpty()) {
            throw new ValidationException("Phone cannot be empty");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }

        DeliveryAgent agent = restaurantRepo.findAll().stream()
                .flatMap(r -> r.getAgents().stream())
                .filter(a -> a.getPhone().equals(phone.trim())
                        && a.getPassword().equals(password.trim()))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Invalid credentials"));

        currentAgent = agent;

        return currentAgent;
    }

    @Override
    public void logout() {
        if (currentAgent != null) {
            System.out.println("ℹ Logged out: " + currentAgent.getName());
        } else {
            System.out.println("⚠ No agent is currently logged in.");
        }

        currentAgent = null;
    }

    @Override
    public DeliveryAgent getCurrentAgent() {
        return currentAgent;
    }

    @Override
    public boolean isLoggedIn() {
        return currentAgent != null;
    }
}