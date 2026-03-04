package com.tss.FoodAppV4.service.interfaces;

import com.tss.FoodAppV4.model.DeliveryAgent;

public interface IAgentAuthService {
    DeliveryAgent login(String phone, String password);
    void logout();
    DeliveryAgent getCurrentAgent();
    boolean isLoggedIn();
}