package com.tss.FoodApp.service.interfaces;

import com.tss.FoodApp.model.DeliveryAgent;

public interface IAgentAuthService {
    DeliveryAgent login(String phone, String password); // use phone as identifier
    void logout();
    DeliveryAgent getCurrentAgent();
    boolean isLoggedIn();
}