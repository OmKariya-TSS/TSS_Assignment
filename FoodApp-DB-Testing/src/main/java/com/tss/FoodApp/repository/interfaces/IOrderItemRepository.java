package com.tss.FoodApp.repository.interfaces;


import com.tss.FoodApp.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface IOrderItemRepository {
    void save(OrderItem orderItem, int orderId);

    Optional<OrderItem> findById(int orderItemId);

    List<OrderItem> findByOrder(int orderId);

    List<OrderItem> findAll();

    void update(OrderItem orderItem,int orderId);

    void delete(int orderItemId);
}