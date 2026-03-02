package com.tss.FoodAppV3.repository.interfaces;


import com.tss.FoodAppV3.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {

    void save(Order order);

    Optional<Order> findById(int orderId);

    List<Order> findByCustomerId(int customerId);

    List<Order> findByRestaurantId(int restaurantId);

    List<Order> findAll();

    void update(Order order);

    void delete(int orderId);
}