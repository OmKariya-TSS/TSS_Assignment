package com.tss.FoodApp.repository.service;

import com.tss.FoodApp.config.DBConnection;
import com.tss.FoodApp.enums.MenuCategory;
import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.MenuItem;
import com.tss.FoodApp.repository.interfaces.IMenuItemRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuItemRepositoryImpl implements IMenuItemRepository {


    @Override
    public MenuItem save(MenuItem item, int restaurantId) {

        if (item == null) throw new ValidationException("Menu item cannot be null.");
        if (restaurantId <= 0) throw new ValidationException("Invalid restaurant ID.");

        String sql = """
        INSERT INTO menu_items
        (restaurant_id, name, price, category, description, is_available, is_spicy, is_best_seller, is_new)
        VALUES (?, ?, ?, ?::menu_category, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"item_id"})) {

            ps.setInt(1, restaurantId);
            ps.setString(2, item.getName());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getCategory().name());
            ps.setString(5, item.getDescription());
            ps.setBoolean(6, item.isAvailable());
            ps.setBoolean(7, item.isSpicy());
            ps.setBoolean(8, item.isBestSeller());
            ps.setBoolean(9, item.isNew());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    MenuItem saved = new MenuItem(generatedId, item.getName(),
                            item.getPrice(), item.getCategory(), item.getDescription());
                    saved.setAvailable(item.isAvailable());
                    saved.setSpicy(item.isSpicy());
                    saved.setBestSeller(item.isBestSeller());
                    saved.setNew(item.isNew());
                    return saved;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save menu item", e);
        }

        throw new RuntimeException("Save succeeded but no ID was returned.");
    }

    @Override
    public Optional<MenuItem> findById(int id) {

        if (id <= 0) throw new ValidationException("Menu item ID must be greater than zero.");

        String sql = """
        SELECT item_id, name, price, category, description,
               is_available, is_spicy, is_best_seller, is_new
        FROM menu_items
        WHERE item_id = ?
        """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch menu item with id: " + id, e);
        }

        return Optional.empty();
    }


    @Override
    public List<MenuItem> findByRestaurant(int restaurantId) {

        if (restaurantId <= 0) throw new ValidationException("Restaurant ID must be greater than zero.");

        List<MenuItem> items = new ArrayList<>();

        String sql = """
        SELECT item_id, name, price, category, description,
               is_available, is_spicy, is_best_seller, is_new
        FROM menu_items
        WHERE restaurant_id = ?
        """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) items.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch menu items for restaurant: " + restaurantId, e);
        }

        return items;
    }


    @Override
    public List<MenuItem> findAll() {

        List<MenuItem> items = new ArrayList<>();

        String sql = """
        SELECT item_id, name, price, category, description,
               is_available, is_spicy, is_best_seller, is_new
        FROM menu_items
        """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) items.add(mapRow(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all menu items", e);
        }

        return items;
    }
    @Override
    public void update(MenuItem item) {

        if (item == null) {
            throw new ValidationException("Menu item cannot be null.");
        }

        if (item.getItemId() <= 0) {
            throw new ValidationException("Cannot update an unsaved menu item — ID is invalid.");
        }

        String sql = """
        UPDATE menu_items
        SET price = ?,
            description = ?,
            is_available = ?,
            is_spicy = ?,
            is_best_seller = ?,
            is_new = ?,
            updated_at = NOW()
        WHERE item_id = ?
        """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, item.getPrice());
            ps.setString(2, item.getDescription());
            ps.setBoolean(3, item.isAvailable());
            ps.setBoolean(4, item.isSpicy());
            ps.setBoolean(5, item.isBestSeller());
            ps.setBoolean(6, item.isNew());
            ps.setInt(7, item.getItemId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Menu item not found with id: " + item.getItemId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update menu item", e);
        }
    }
    @Override
    public void delete(int id) {

        if (id <= 0) {
            throw new ValidationException("Menu item ID must be greater than zero.");
        }

        String sql = "DELETE FROM menu_items WHERE item_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Menu item not found with id: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete menu item with id: " + id, e);
        }
    }

    private MenuItem mapRow(ResultSet rs) throws SQLException {
        int itemId = rs.getInt("item_id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        MenuCategory category = MenuCategory.valueOf(rs.getString("category").toUpperCase());
        String description = rs.getString("description");
        boolean isAvailable = rs.getBoolean("is_available");
        boolean isSpicy = rs.getBoolean("is_spicy");
        boolean isBestSeller = rs.getBoolean("is_best_seller");
        boolean isNew = rs.getBoolean("is_new");

        MenuItem item = new MenuItem(itemId, name, price, category, description);
        item.setAvailable(isAvailable);
        item.setSpicy(isSpicy);
        item.setBestSeller(isBestSeller);
        item.setNew(isNew);
        return item;
    }
}