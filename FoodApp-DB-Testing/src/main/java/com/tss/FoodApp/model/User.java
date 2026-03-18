package com.tss.FoodApp.model;

import com.tss.FoodApp.enums.UserRole;
import com.tss.FoodApp.exceptions.ValidationException;

public abstract class User {

    private int userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserRole role;

    public User(int userId,
                String name,
                String email,
                String password,
                String phone,
                UserRole role) {

        validateUserId(userId);
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.password = validatePassword(password);
        this.phone = validatePhone(phone);
        validateRole(role);

        this.userId = userId;
        this.role = role;
    }

    protected User() {
    }

    private void validateUserId(int userId) {
        if (userId <= 0) {
            throw new ValidationException("User ID must be positive");
        }
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name cannot be null or empty");
        }
        return name.trim();
    }

    private String validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("Email cannot be null or empty");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationException("Invalid email format");
        }

        return email.trim();
    }

    private String validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new ValidationException("Password cannot be null or empty");
        }

        if (password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters long");
        }

        return password;
    }

    private String validatePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new ValidationException("Phone number cannot be null or empty");
        }

        if (!phone.matches("\\d{10}")) {
            throw new ValidationException("Phone number must contain exactly 10 digits");
        }

        return phone;
    }

    private void validateRole(UserRole role) {
        if (role == null) {
            throw new ValidationException("User role cannot be null");
        }
    }

    public abstract void showDashboard();

    public int getUserId() { return userId; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getPhone() { return phone; }

    public UserRole getRole() { return role; }

    public void setUserId(int userId) {
        validateUserId(userId);
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setEmail(String email) {
        this.email = validateEmail(email);
    }

    public void setPassword(String password) {
        this.password = validatePassword(password);
    }

    public void setPhone(String phone) {
        this.phone = validatePhone(phone);
    }

    public void setRole(UserRole role) {
        validateRole(role);
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format(
                "User ID: %d | Name: %s | Email: %s | Phone: %s | Role: %s",
                userId,
                name,
                email,
                phone,
                role
        );
    }
}