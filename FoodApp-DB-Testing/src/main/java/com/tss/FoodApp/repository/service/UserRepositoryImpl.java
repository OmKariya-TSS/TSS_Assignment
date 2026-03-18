package com.tss.FoodApp.repository.service;

import com.tss.FoodApp.enums.UserRole;
import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.Admin;
import com.tss.FoodApp.model.Customer;
import com.tss.FoodApp.model.User;
import com.tss.FoodApp.repository.interfaces.IUserRepository;
import com.tss.FoodApp.config.DBConnection;

import java.sql.*;
import java.util.*;

public class UserRepositoryImpl implements IUserRepository {

    public UserRepositoryImpl() {}

    @Override
    public void save(User user) {

        if (user == null) {
            throw new ValidationException("User cannot be null.");
        }

        try (Connection conn = DBConnection.connect()) {

            if (user instanceof Customer customer) {

                String sql = """
                    INSERT INTO customers
                    (name, email, password, phone, address)
                    VALUES (?, ?, ?, ?, ?)
                    """;

                try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"customer_id"})) {

                    ps.setString(1, customer.getName());
                    ps.setString(2, customer.getEmail());
                    ps.setString(3, customer.getPassword());
                    ps.setString(4, customer.getPhone());
                    ps.setString(5, customer.getAddress());

                    ps.executeUpdate();

                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            customer.setUserId(rs.getInt(1));
                        }
                    }
                }

            } else if (user instanceof Admin admin) {

                String sql = """
                    INSERT INTO admins
                    (name, email, password, phone, admin_code)
                    VALUES (?, ?, ?, ?, ?)
                    """;

                try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"admin_id"})) {

                    ps.setString(1, admin.getName());
                    ps.setString(2, admin.getEmail());
                    ps.setString(3, admin.getPassword());
                    ps.setString(4, admin.getPhone());
                    ps.setString(5, admin.getAdminCode());

                    ps.executeUpdate();

                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            admin.setUserId(rs.getInt(1));
                        }
                    }
                }

            } else {
                throw new ValidationException("Unsupported user type.");
            }

        } catch (SQLException e) {
            if (e.getSQLState() != null && e.getSQLState().startsWith("23")) {
                throw new ValidationException("Email already registered.");
            }
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public Optional<User> findById(int userId) {

        if (userId <= 0) {
            throw new ValidationException("User ID must be greater than zero.");
        }

        try (Connection conn = DBConnection.connect()) {

            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM customers WHERE customer_id = ?")) {

                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapCustomer(rs));
                    }
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM admins WHERE admin_id = ?")) {

                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapAdmin(rs));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch user", e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new ValidationException("Email cannot be null or empty.");
        }

        try (Connection conn = DBConnection.connect()) {

            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM customers WHERE LOWER(email) = LOWER(?)")) {

                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapCustomer(rs));
                    }
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM admins WHERE LOWER(email) = LOWER(?)")) {

                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapAdmin(rs));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch user by email", e);
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapCustomer(rs));
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM admins");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapAdmin(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch users", e);
        }

        return users;
    }

    @Override
    public void update(User user) {

        if (user == null) {
            throw new ValidationException("User cannot be null.");
        }

        if (user.getUserId() <= 0) {
            throw new ValidationException("Invalid User ID.");
        }

        try (Connection conn = DBConnection.connect()) {

            if (user instanceof Customer customer) {

                String sql = """
                    UPDATE customers
                    SET name = ?,
                        email = ?,
                        password = ?,
                        phone = ?,
                        address = ?,
                        updated_at = NOW()
                    WHERE customer_id = ?
                    """;

                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, customer.getName());
                    ps.setString(2, customer.getEmail());
                    ps.setString(3, customer.getPassword());
                    ps.setString(4, customer.getPhone());
                    ps.setString(5, customer.getAddress());
                    ps.setInt(6, customer.getUserId());

                    int rows = ps.executeUpdate();
                    if (rows == 0) {
                        throw new ValidationException("Customer not found.");
                    }
                }

            } else if (user instanceof Admin admin) {

                String sql = """
                    UPDATE admins
                    SET name = ?,
                        email = ?,
                        password = ?,
                        phone = ?,
                        admin_code = ?,
                        updated_at = NOW()
                    WHERE admin_id = ?
                    """;

                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, admin.getName());
                    ps.setString(2, admin.getEmail());
                    ps.setString(3, admin.getPassword());
                    ps.setString(4, admin.getPhone());
                    ps.setString(5, admin.getAdminCode());
                    ps.setInt(6, admin.getUserId());

                    int rows = ps.executeUpdate();
                    if (rows == 0) {
                        throw new ValidationException("Admin not found.");
                    }
                }

            } else {
                throw new ValidationException("Unsupported user type.");
            }

        } catch (SQLException e) {
            if (e.getSQLState() != null && e.getSQLState().startsWith("23")) {
                throw new ValidationException("Email already in use by another account.");
            }
            throw new RuntimeException("Failed to update user", e);
        }
    }

    @Override
    public void delete(int userId) {

        if (userId <= 0) {
            throw new ValidationException("Invalid User ID.");
        }

        try (Connection conn = DBConnection.connect()) {

            try (PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM customers WHERE customer_id = ?")) {
                ps.setInt(1, userId);
                if (ps.executeUpdate() > 0) return;
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM admins WHERE admin_id = ?")) {
                ps.setInt(1, userId);
                if (ps.executeUpdate() > 0) return;
            }

            throw new ValidationException("User not found.");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setRole(UserRole.CUSTOMER);
        customer.setUserId(rs.getInt("customer_id"));
        customer.setName(rs.getString("name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setPhone(rs.getString("phone"));
        customer.setAddress(rs.getString("address"));
        return customer;
    }

    private Admin mapAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setRole(UserRole.ADMIN);
        admin.setUserId(rs.getInt("admin_id"));
        admin.setName(rs.getString("name"));
        admin.setEmail(rs.getString("email"));
        admin.setPassword(rs.getString("password"));
        admin.setPhone(rs.getString("phone"));
        admin.setAdminCode(rs.getString("admin_code"));
        return admin;
    }
}