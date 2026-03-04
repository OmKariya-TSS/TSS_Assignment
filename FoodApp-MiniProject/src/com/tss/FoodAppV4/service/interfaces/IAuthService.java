package com.tss.FoodAppV4.service.interfaces;

import com.tss.FoodAppV4.model.Customer;
import com.tss.FoodAppV4.model.User;

public interface IAuthService {


    User login(String email, String password);


    boolean register(Customer customer);

    void logout();


    User getCurrentUser();



    boolean isLoggedIn();
}