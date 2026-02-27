package com.tss.FoodApp.model;

import com.tss.FoodApp.enums.UserRole;
import com.tss.FoodApp.exceptions.ValidationException;

public class Admin extends User {

    private String adminCode;

    public Admin(int userId,
                 String name,
                 String email,
                 String password,
                 String phone,
                 String adminCode) {

        super(userId, name, email, password, phone, UserRole.ADMIN);

        validateAdminCode(adminCode);
        this.adminCode = adminCode;
    }


    private void validateAdminCode(String adminCode) {

        if (adminCode == null || adminCode.isBlank()) {
            throw new ValidationException("Admin code cannot be null or empty");
        }

        if (adminCode.length() < 4) {
            throw new ValidationException("Admin code must be at least 4 characters long");
        }

        if (!adminCode.matches("[A-Z0-9]+")) {
            throw new ValidationException("Admin code must contain only uppercase letters and digits");
        }
    }

    public String getAdminCode() {
        return adminCode;
    }

    @Override
    public void showDashboard() {
        System.out.println("\n=================================");
        System.out.println("        ADMIN DASHBOARD          ");
        System.out.println("=================================");
        System.out.println("Welcome, " + getName());
        System.out.println("Role: " + getRole());
        System.out.println("=================================\n");
    }

    @Override
    public String toString() {
        return super.toString() + " | Admin Code: " + adminCode;
    }
}