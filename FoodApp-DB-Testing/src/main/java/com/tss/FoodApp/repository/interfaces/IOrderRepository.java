package com.tss.FoodApp.repository.interfaces;


import com.tss.FoodApp.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {

    void save(Order order);

    Optional<Order> findById(int orderId);

    List<Order> findByCustomerId(int customerId);

    List<Order> findAll();

    void update(Order order);

    void delete(int orderId);
}