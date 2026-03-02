package com.tss.FoodAppV3.factory;

import com.tss.FoodAppV3.enums.UserRole;
import com.tss.FoodAppV3.model.Admin;
import com.tss.FoodAppV3.model.Customer;
import com.tss.FoodAppV3.model.User;

public class UserFactory {
    private static int idCounter = 1;
    public static User createUser(String role,
                                  String name,
                                  String email,
                                  String password,
                                  String phone,
                                  String extraParam) {
        if (role.equalsIgnoreCase(UserRole.ADMIN.name())) {
            return new Admin(
                    idCounter++,
                    name,
                    email,
                    password,
                    phone,
                    extraParam //admincode
            );
        } else if (role.equalsIgnoreCase(UserRole.CUSTOMER.name())) {
            return new Customer(
                    idCounter++,
                    name,
                    email,
                    password,
                    phone,
                    extraParam   // address
            );
        } else {
            throw new IllegalArgumentException("Invalid user role: " + role);
        }
    }
}