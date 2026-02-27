package com.tss.FoodApp.repository.interfaces;

import com.tss.FoodApp.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    void save(User user);

    Optional<User> findById(int userId);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void update(User user);

    void delete(int userId);
}