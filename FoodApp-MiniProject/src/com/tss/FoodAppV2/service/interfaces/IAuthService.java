package com.tss.FoodAppV2.service.interfaces;

import com.tss.FoodAppV2.model.Customer;
import com.tss.FoodAppV2.model.User;

public interface IAuthService {


    User login(String email, String password);


    boolean register(Customer customer);

    void logout();


    User getCurrentUser();



    boolean isLoggedIn();
}