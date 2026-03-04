package com.tss.FoodAppV4.repository.service;

import com.tss.FoodAppV4.exceptions.ValidationException;
import com.tss.FoodAppV4.model.User;
import com.tss.FoodAppV4.serialization.PersistenceManager;
import com.tss.FoodAppV4.repository.interfaces.IUserRepository;

import java.util.*;

public class UserRepositoryImpl implements IUserRepository {

    private static final String FILE = "users.dat";
    private Map<Integer, User> store = new HashMap<>();
    private int idCounter = 1;


    public UserRepositoryImpl() {
        this.store = PersistenceManager.load(FILE, new HashMap<>());
        this.idCounter = store.keySet().stream()
                .mapToInt(Integer::intValue)
                .max().orElse(0) + 1;
    }
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
        PersistenceManager.save(FILE, store);

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
        PersistenceManager.save(FILE, store);
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
        PersistenceManager.save(FILE, store);
    }
}