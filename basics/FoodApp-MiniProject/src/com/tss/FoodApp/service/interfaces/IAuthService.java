package com.tss.FoodApp.service.interfaces;

import com.tss.FoodApp.model.Customer;
import com.tss.FoodApp.model.User;

public interface IAuthService {


    User login(String email, String password);


    boolean register(Customer customer);

    void logout();


    User getCurrentUser();



    boolean isLoggedIn();
}