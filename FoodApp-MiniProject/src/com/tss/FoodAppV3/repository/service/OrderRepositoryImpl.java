package com.tss.FoodAppV3.repository.service;

import com.tss.FoodAppV3.exceptions.ValidationException;
import com.tss.FoodAppV3.model.Order;
import com.tss.FoodAppV3.repository.interfaces.IOrderRepository;
import com.tss.FoodAppV3.serialization.PersistenceManager;

import java.util.*;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements IOrderRepository {
    private static final String FILE = "orders.dat";
    private Map<Integer, Order> store = new HashMap<>();
    private int idCounter = 1;
    public OrderRepositoryImpl() {
        this.store = PersistenceManager.load(FILE, new HashMap<>());
        this.idCounter = store.keySet().stream()
                .mapToInt(Integer::intValue)
                .max().orElse(0) + 1;
    }

    @Override
    public void save(Order order) {

        if (order == null) {
            throw new ValidationException("Order cannot be null.");
        }

        if (order.getOrderId() < 0) {
            throw new ValidationException("Order ID cannot be negative.");
        }

        if (order.getOrderId() == 0) {
            order.setOrderId(idCounter++);
        } else {
            if (order.getOrderId() >= idCounter) {
                idCounter = order.getOrderId() + 1;
            }
        }

        store.put(order.getOrderId(), order);
        PersistenceManager.save(FILE, store);


    }

    @Override
    public Optional<Order> findById(int orderId) {

        if (orderId <= 0) {
            throw new ValidationException("Order ID must be greater than zero.");
        }

        return Optional.ofNullable(store.get(orderId));
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {

        if (customerId <= 0) {
            throw new ValidationException("Customer ID must be greater than zero.");
        }

        return store.values()
                .stream()
                .filter(o -> o.getCustomer() != null
                        && o.getCustomer().getUserId() == customerId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByRestaurantId(int restaurantId) {

        if (restaurantId <= 0) {
            throw new ValidationException("Restaurant ID must be greater than zero.");
        }

        return store.values()
                .stream()
                .filter(o -> o.getRestaurant() != null
                        && o.getRestaurant().getRestaurantId() == restaurantId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAll() {
        return Collections.unmodifiableList(
                new ArrayList<>(store.values())
        );
    }

    @Override
    public void update(Order order) {

        if (order == null) {
            throw new ValidationException("Order cannot be null.");
        }

        if (order.getOrderId() <= 0) {
            throw new ValidationException("Invalid Order ID.");
        }

        if (!store.containsKey(order.getOrderId())) {
            throw new ValidationException("Order does not exist.");
        }

        store.put(order.getOrderId(), order);
        PersistenceManager.save(FILE, store);
    }

    @Override
    public void delete(int orderId) {

        if (orderId <= 0) {
            throw new ValidationException("Invalid Order ID.");
        }

        if (!store.containsKey(orderId)) {
            throw new ValidationException("Order not found.");
        }

        store.remove(orderId);
        PersistenceManager.save(FILE, store);
    }
}