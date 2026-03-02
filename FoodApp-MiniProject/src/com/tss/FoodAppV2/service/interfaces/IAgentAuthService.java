package com.tss.FoodAppV2.service.interfaces;

import com.tss.FoodAppV2.model.DeliveryAgent;

public interface IAgentAuthService {
    DeliveryAgent login(String phone, String password); // use phone as identifier
    void logout();
    DeliveryAgent getCurrentAgent();
    boolean isLoggedIn();
}