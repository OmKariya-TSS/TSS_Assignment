package com.tss.FoodApp.repository.service;

import com.tss.FoodApp.config.DBConnection;
import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.MenuItem;
import com.tss.FoodApp.model.OrderItem;
import com.tss.FoodApp.repository.interfaces.IOrderItemRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderItemRepositoryImpl implements IOrderItemRepository {

    @Override
    public void save(OrderItem orderItem, int orderId) {

        if (orderItem == null) {
            throw new ValidationException("OrderItem cannot be null.");
        }

        if (orderId <= 0) {
            throw new ValidationException("Invalid Order ID.");
        }

        if (orderItem.getMenuItem() == null) {
            throw new ValidationException("OrderItem must have a valid MenuItem.");
        }

        String sql = """
            INSERT INTO order_items
            (order_id, item_id, item_name, item_price, quantity, item_total)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, orderItem.getMenuItem().getItemId());
            ps.setString(3, orderItem.getMenuItem().getName());
            ps.setDouble(4, orderItem.getMenuItem().getPrice());
            ps.setInt(5, orderItem.getQuantity());
            ps.setDouble(6, orderItem.getItemTotal());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save order item", e);
        }
    }

    @Override
    public Optional<OrderItem> findById(int orderItemId) {

        if (orderItemId <= 0) {
            throw new ValidationException("OrderItem ID must be greater than zero.");
        }

        String sql = "SELECT * FROM order_items WHERE order_item_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch order item by ID", e);
        }

        return Optional.empty();
    }

    @Override
    public List<OrderItem> findByOrder(int orderId) {

        if (orderId <= 0) {
            throw new ValidationException("Order ID must be greater than zero.");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orderItems.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch order items for order " + orderId, e);
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> findAll() {

        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                orderItems.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all order items", e);
        }

        return orderItems;
    }
    @Override
    public void update(OrderItem orderItem, int orderId) {

        if (orderItem == null) {
            throw new ValidationException("OrderItem cannot be null.");
        }

        if (orderId <= 0) {
            throw new ValidationException("Invalid Order ID.");
        }

        if (orderItem.getMenuItem() == null || orderItem.getMenuItem().getItemId() <= 0) {
            throw new ValidationException("OrderItem must have a valid MenuItem.");
        }

        String sql = """
            UPDATE order_items
            SET quantity = ?,
                item_total = ?
            WHERE order_id = ? AND item_id = ?
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItem.getQuantity());
            ps.setDouble(2, orderItem.getItemTotal());
            ps.setInt(3, orderId);
            ps.setInt(4, orderItem.getMenuItem().getItemId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ValidationException("OrderItem does not exist for this order.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update order item", e);
        }
    }

    @Override
    public void delete(int orderItemId) {

        if (orderItemId <= 0) {
            throw new ValidationException("Invalid OrderItem ID.");
        }

        String sql = "DELETE FROM order_items WHERE order_item_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ValidationException("OrderItem not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete order item", e);
        }
    }

    private OrderItem mapRow(ResultSet rs) throws SQLException {
        MenuItem menuItem = new MenuItem(
                rs.getInt("item_id"),
                rs.getString("item_name"),
                rs.getDouble("item_price"),
                null,
                ""
        );

        return new OrderItem(menuItem, rs.getInt("quantity"));
    }
}