package com.tss.FoodAppV3.service.interfaces;

import com.tss.FoodAppV3.model.DeliveryAgent;

public interface IAgentAuthService {
    DeliveryAgent login(String phone, String password);
    void logout();
    DeliveryAgent getCurrentAgent();
    boolean isLoggedIn();
}