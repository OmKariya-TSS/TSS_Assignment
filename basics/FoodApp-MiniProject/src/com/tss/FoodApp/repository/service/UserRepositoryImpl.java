package com.tss.FoodApp.repository.service;

import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.User;
import com.tss.FoodApp.repository.interfaces.IUserRepository;

import java.util.*;

public class UserRepositoryImpl implements IUserRepository {

    private final Map<Integer, User> store = new HashMap<>();
    private int idCounter = 1;

    @Override
    public void save(User user) {

        if (user == null) {
            throw new ValidationException("User cannot be null.");
        }

        if (user.getUserId() < 0) {
            throw new ValidationException("User ID cannot be negative.");
        }

        if (findByEmail(user.getEmail()).isPresent()) {
            throw new ValidationException("Email already registered.");
        }

        if (user.getUserId() == 0) {
            user.setUserId(idCounter++);
        } else {
            if (user.getUserId() >= idCounter) {
                idCounter = user.getUserId() + 1;
            }
        }

        store.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findById(int userId) {

        if (userId <= 0) {
            throw new ValidationException("User ID must be greater than zero.");
        }

        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public Optional<User> findByEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new ValidationException("Email cannot be null or empty.");
        }

        return store.values()
                .stream()
                .filter(u -> u.getEmail() != null
                        && u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return Collections.unmodifiableList(
                new ArrayList<>(store.values())
        );
    }

    @Override
    public void update(User user) {

        if (user == null) {
            throw new ValidationException("User cannot be null.");
        }

        if (user.getUserId() <= 0) {
            throw new ValidationException("Invalid User ID.");
        }

        if (!store.containsKey(user.getUserId())) {
            throw new ValidationException("User does not exist.");
        }

        store.put(user.getUserId(), user);
    }

    @Override
    public void delete(int userId) {

        if (userId <= 0) {
            throw new ValidationException("Invalid User ID.");
        }

        if (!store.containsKey(userId)) {
            throw new ValidationException("User not found.");
        }

        store.remove(userId);
    }
}