package com.tss.FoodAppV3.service.implementations;

import com.tss.FoodAppV3.exceptions.ValidationException;
import com.tss.FoodAppV3.model.Customer;
import com.tss.FoodAppV3.model.User;
import com.tss.FoodAppV3.repository.interfaces.IUserRepository;
import com.tss.FoodAppV3.service.interfaces.IAuthService;

import java.util.Optional;

public class AuthServiceImpl implements IAuthService {

    private final IUserRepository userRepo;
    private User currentUser = null;

    public AuthServiceImpl(IUserRepository userRepo) {

        if (userRepo == null) {
            throw new ValidationException("User repository cannot be null.");
        }

        this.userRepo = userRepo;
    }

    @Override
    public User login(String email, String password) {

        if (email == null || email.isBlank()) {
            throw new ValidationException("Email cannot be empty.");
        }

        if (password == null || password.isBlank()) {
            throw new ValidationException("Password cannot be empty.");
        }

        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new ValidationException("User with email " + email + " not found.");
        }

        User user = userOpt.get();

        if (user.getPassword() == null || !user.getPassword().equals(password)) {
            throw new ValidationException("Incorrect password.");
        }

        currentUser = user;

        System.out.println("✅ Logged in as " + user.getName() + " (" + user.getRole() + ")");

        return user;
    }

    @Override
    public boolean register(Customer customer) {

        if (customer == null) {
            throw new ValidationException("Customer cannot be null.");
        }

        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            throw new ValidationException("Customer email cannot be empty.");
        }

        Optional<User> existing = userRepo.findByEmail(customer.getEmail());

        if (existing.isPresent()) {
            throw new ValidationException("Email already registered.");
        }

        userRepo.save(customer);

        System.out.println("✅ Registration successful for " + customer.getName());

        return true;
    }

    @Override
    public void logout() {

        if (currentUser != null) {
            System.out.println("ℹ Logged out: " + currentUser.getName());
        }

        currentUser = null;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}