package com.tss.FoodApp.repository.service;

import com.tss.FoodApp.config.DBConnection;
import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.MenuItem;
import com.tss.FoodApp.model.Restaurant;
import com.tss.FoodApp.repository.interfaces.IDeliveryAgentRepository;
import com.tss.FoodApp.repository.interfaces.IMenuItemRepository;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;

import java.sql.*;
import java.util.*;

public class RestaurantRepositoryImpl implements IRestaurantRepository {

    private final IMenuItemRepository menuRepo;
    private final IDeliveryAgentRepository agentRepo;

    public RestaurantRepositoryImpl(IMenuItemRepository menuRepo,
                                    IDeliveryAgentRepository agentRepo) {
        this.menuRepo = menuRepo;
        this.agentRepo = agentRepo;
    }

    @Override
    public void save(Restaurant restaurant) {

        if (restaurant == null) {
            throw new ValidationException("Restaurant cannot be null.");
        }

        String sql = """
            INSERT INTO restaurants
            (name, location, cuisine_type, is_open)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"restaurant_id"})) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getLocation());
            ps.setString(3, restaurant.getCuisineType());
            ps.setBoolean(4, restaurant.isOpen());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int restaurantId = rs.getInt(1);
                    restaurant.setRestaurantId(restaurantId);

                    if (restaurant.getMenu() != null) {
                        List<MenuItem> savedMenu = new ArrayList<>();
                        for (MenuItem item : restaurant.getMenu()) {
                            MenuItem saved = menuRepo.save(item, restaurantId);
                            savedMenu.add(saved);
                        }
                        restaurant.setMenu(savedMenu);
                    }

                    if (restaurant.getAgents() != null) {
                        for (DeliveryAgent agent : restaurant.getAgents()) {
                            agentRepo.save(agent, restaurantId);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save restaurant", e);
        }
    }

    @Override
    public Optional<Restaurant> findById(int id) {

        if (id <= 0) {
            throw new ValidationException("Restaurant ID must be greater than zero.");
        }

        String sql = "SELECT * FROM restaurants WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Restaurant restaurant = mapRow(rs);
                    return Optional.of(restaurant);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch restaurant by id", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Restaurant> findAll() {

        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurants";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                restaurants.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch restaurants", e);
        }

        return restaurants;
    }

    @Override
    public void update(Restaurant restaurant) {

        if (restaurant == null) {
            throw new ValidationException("Restaurant cannot be null.");
        }

        if (restaurant.getRestaurantId() <= 0) {
            throw new ValidationException("Invalid Restaurant ID.");
        }

        String sql = """
            UPDATE restaurants
            SET name = ?,
                location = ?,
                cuisine_type = ?,
                is_open = ?,
                updated_at = NOW()
            WHERE restaurant_id = ?
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getLocation());
            ps.setString(3, restaurant.getCuisineType());
            ps.setBoolean(4, restaurant.isOpen());
            ps.setInt(5, restaurant.getRestaurantId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ValidationException("Restaurant does not exist.");
            }

            if (restaurant.getMenu() != null) {
                for (MenuItem item : restaurant.getMenu()) {
                    if (item.getItemId() > 0) {
                        menuRepo.update(item);
                    }
                }
            }

            if (restaurant.getAgents() != null) {
                for (DeliveryAgent agent : restaurant.getAgents()) {
                    if (agent.getAgentId() > 0) {
                        agentRepo.update(agent);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update restaurant", e);
        }
    }

    @Override
    public void delete(int restaurantId) {

        if (restaurantId <= 0) {
            throw new ValidationException("Invalid Restaurant ID.");
        }

        String sql = "DELETE FROM restaurants WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ValidationException("Restaurant not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete restaurant", e);
        }
    }

    private Restaurant mapRow(ResultSet rs) throws SQLException {

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("restaurant_id"));
        restaurant.setName(rs.getString("name"));
        restaurant.setLocation(rs.getString("location"));
        restaurant.setCuisineType(rs.getString("cuisine_type"));
        restaurant.setOpen(rs.getBoolean("is_open"));

        List<MenuItem> menu = menuRepo.findByRestaurant(restaurant.getRestaurantId());
        restaurant.setMenu(menu);

        List<DeliveryAgent> agents = agentRepo.findByRestaurant(restaurant.getRestaurantId());
        restaurant.setAgents(agents);

        return restaurant;
    }
}