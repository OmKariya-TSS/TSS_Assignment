package com.tss.FoodApp.repository.impl;

import com.tss.FoodApp.config.DBConnection;
import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.repository.interfaces.IDeliveryAgentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryAgentRepositoryImpl implements IDeliveryAgentRepository {

    @Override
    public void save(DeliveryAgent agent, int restaurantId) {

        if (agent == null) {
            throw new ValidationException("Delivery agent cannot be null.");
        }

        if (restaurantId <= 0) {
            throw new ValidationException("Invalid restaurant ID.");
        }

        String sql = """
            INSERT INTO delivery_agents
            (restaurant_id, name, phone, password, is_available, total_deliveries)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"agent_id"})) {

            ps.setInt(1, restaurantId);
            ps.setString(2, agent.getName());
            ps.setString(3, agent.getPhone());
            ps.setString(4, agent.getPassword());
            ps.setBoolean(5, agent.isAvailable());
            ps.setInt(6, agent.getTotalDeliveries());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    agent.setAgentId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save delivery agent", e);
        }
    }

    @Override
    public Optional<DeliveryAgent> findById(int agentId) {

        if (agentId <= 0) {
            throw new ValidationException("Agent ID must be greater than zero.");
        }

        String sql = "SELECT * FROM delivery_agents WHERE agent_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, agentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String password = rs.getString("password");
                    boolean isAvailable = rs.getBoolean("is_available");
                    int totalDeliveries = rs.getInt("total_deliveries");

                    DeliveryAgent agent = new DeliveryAgent(agentId, name, phone, password);
                    agent.setAvailable(isAvailable);
                    agent.setTotalDeliveries(totalDeliveries);

                    return Optional.of(agent);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch delivery agent by ID", e);
        }

        return Optional.empty();
    }

    @Override
    public List<DeliveryAgent> findByRestaurant(int restaurantId) {

        if (restaurantId <= 0) {
            throw new ValidationException("Restaurant ID must be greater than zero.");
        }

        List<DeliveryAgent> agents = new ArrayList<>();
        String sql = "SELECT * FROM delivery_agents WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    agents.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch agents for restaurant ID " + restaurantId, e);
        }

        return agents;
    }

    @Override
    public List<DeliveryAgent> findAll() {

        List<DeliveryAgent> agents = new ArrayList<>();
        String sql = "SELECT * FROM delivery_agents";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                agents.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all delivery agents", e);
        }

        return agents;
    }

    @Override
    public void update(DeliveryAgent agent) {

        if (agent == null) {
            throw new ValidationException("Delivery agent cannot be null.");
        }

        if (agent.getAgentId() <= 0) {
            throw new ValidationException("Invalid agent ID.");
        }

        String sql = """
            UPDATE delivery_agents
            SET name = ?,
                phone = ?,
                password = ?,
                is_available = ?,
                total_deliveries = ?,
                updated_at = NOW()
            WHERE agent_id = ?
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, agent.getName());
            ps.setString(2, agent.getPhone());
            ps.setString(3, agent.getPassword());
            ps.setBoolean(4, agent.isAvailable());
            ps.setInt(5, agent.getTotalDeliveries());
            ps.setInt(6, agent.getAgentId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ValidationException("Delivery agent not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update delivery agent", e);
        }
    }

    @Override
    public void delete(int agentId) {

        if (agentId <= 0) {
            throw new ValidationException("Invalid agent ID.");
        }

        String sql = "DELETE FROM delivery_agents WHERE agent_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, agentId);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ValidationException("Delivery agent not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete delivery agent", e);
        }
    }

    private DeliveryAgent mapRow(ResultSet rs) throws SQLException {
        int agentId = rs.getInt("agent_id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String password = rs.getString("password");
        boolean isAvailable = rs.getBoolean("is_available");
        int totalDeliveries = rs.getInt("total_deliveries");

        DeliveryAgent agent = new DeliveryAgent(agentId, name, phone, password);
        agent.setAvailable(isAvailable);
        agent.setTotalDeliveries(totalDeliveries);
        return agent;
    }
}