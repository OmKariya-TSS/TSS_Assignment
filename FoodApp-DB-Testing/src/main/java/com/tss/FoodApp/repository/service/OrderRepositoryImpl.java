package com.tss.FoodApp.repository.service;

import com.tss.FoodApp.config.DBConnection;
import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.enums.PaymentMethod;
import com.tss.FoodApp.enums.PaymentStatus;
import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.Customer;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.model.OrderItem;
import com.tss.FoodApp.repository.interfaces.*;
import com.tss.FoodApp.model.*;
import com.tss.FoodApp.repository.interfaces.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements IOrderRepository {

    private final IUserRepository userRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IOrderItemRepository orderItemRepo;
    private final IDeliveryAgentRepository agentRepo;
    private final IMenuItemRepository menuRepo;

    public OrderRepositoryImpl(IUserRepository userRepository,
                               IRestaurantRepository restaurantRepository,
                               IOrderItemRepository orderItemRepo,
                               IDeliveryAgentRepository agentRepo,
                               IMenuItemRepository menuRepo) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderItemRepo = orderItemRepo;
        this.agentRepo = agentRepo;
        this.menuRepo = menuRepo;
    }

    @Override
    public void save(Order order) {

        if (order == null) {
            throw new ValidationException("Order cannot be null.");
        }

        if (order.getCustomer() == null || order.getCustomer().getUserId() <= 0) {
            throw new ValidationException("Order must have a valid customer.");
        }

        if (order.getRestaurant() == null || order.getRestaurant().getRestaurantId() <= 0) {
            throw new ValidationException("Order must have a valid restaurant.");
        }

        String sql = """
            INSERT INTO orders
            (customer_id, restaurant_id, agent_id, status,
             payment_method, payment_status,
             subtotal, discount_amount, final_total, special_note)
            VALUES (?,?,?,?::order_status,?::payment_method,?::payment_status,?,?,?,?)
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"order_id"})) {

            ps.setInt(1, order.getCustomer().getUserId());
            ps.setInt(2, order.getRestaurant().getRestaurantId());

            if (order.getAssignedAgent() != null)
                ps.setInt(3, order.getAssignedAgent().getAgentId());
            else
                ps.setNull(3, Types.INTEGER);

            ps.setString(4, order.getStatus().name());
            ps.setString(5, order.getPaymentMethod().name());
            ps.setString(6, order.getPaymentStatus().name());
            ps.setDouble(7, order.getSubtotal());
            ps.setDouble(8, order.getDiscountAmount());
            ps.setDouble(9, order.getFinalTotal());
            ps.setString(10, order.getSpecialNote());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int orderId = rs.getInt(1);
                    order.setOrderId(orderId);

                    if (order.getItems() != null && !order.getItems().isEmpty()) {
                        for (OrderItem item : order.getItems()) {
                            orderItemRepo.save(item, orderId);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save order", e);
        }
    }

    @Override
    public Optional<Order> findById(int orderId) {

        if (orderId <= 0) {
            throw new ValidationException("Order ID must be greater than zero.");
        }

        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs, conn));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find order by ID", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {

        if (customerId <= 0) {
            throw new ValidationException("Customer ID must be greater than zero.");
        }

        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(mapRow(rs, conn));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch orders for customer", e);
        }

        return orders;
    }

    @Override
    public List<Order> findByRestaurantId(int restaurantId) {

        if (restaurantId <= 0) {
            throw new ValidationException("Restaurant ID must be greater than zero.");
        }

        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(mapRow(rs, conn));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch orders for restaurant", e);
        }

        return orders;
    }

    @Override
    public List<Order> findAll() {

        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                orders.add(mapRow(rs, conn));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all orders", e);
        }

        return orders;
    }

    @Override
    public void update(Order order) {

        if (order == null) {
            throw new ValidationException("Order cannot be null.");
        }

        if (order.getOrderId() <= 0) {
            throw new ValidationException("Invalid Order ID.");
        }

        String sql = """
            UPDATE orders
            SET status = ?::order_status,
                payment_status = ?::payment_status,
                agent_id = ?
            WHERE order_id = ?
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, order.getStatus().name());
            ps.setString(2, order.getPaymentStatus().name());

            if (order.getAssignedAgent() != null)
                ps.setInt(3, order.getAssignedAgent().getAgentId());
            else
                ps.setNull(3, Types.INTEGER);

            ps.setInt(4, order.getOrderId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ValidationException("Order not found.");
            }

            if (order.getItems() != null) {
                for (OrderItem item : order.getItems()) {
                    orderItemRepo.update(item, order.getOrderId());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update order", e);
        }
    }

    @Override
    public void delete(int orderId) {

        if (orderId <= 0) {
            throw new ValidationException("Invalid Order ID.");
        }

        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ValidationException("Order not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete order", e);
        }
    }

    private Order mapRow(ResultSet rs, Connection conn) throws SQLException {

        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setStatus(OrderStatus.valueOf(rs.getString("status").toUpperCase()));
        order.setPaymentMethod(PaymentMethod.valueOf(rs.getString("payment_method").toUpperCase()));
        order.setPaymentStatus(PaymentStatus.valueOf(rs.getString("payment_status").toUpperCase()));
        order.setSpecialNote(rs.getString("special_note"));
        order.setOrderTime(rs.getTimestamp("order_time").toLocalDateTime());


        int customerId = rs.getInt("customer_id");
        userRepository.findById(customerId).ifPresent(user -> {
            Customer cust = new Customer();
            cust.setUserId(user.getUserId());
            cust.setName(user.getName());
            cust.setPhone(user.getPhone());
            cust.setEmail(user.getEmail());
            cust.setPassword(user.getPassword());
            if (user instanceof Customer fullCustomer) {
                cust.setAddress(fullCustomer.getAddress());
            }
            order.setCustomer(cust);
        });

        int restaurantId = rs.getInt("restaurant_id");
        restaurantRepository.findById(restaurantId).ifPresent(order::setRestaurant);

        int agentId = rs.getInt("agent_id");
        if (!rs.wasNull()) {
            agentRepo.findById(agentId).ifPresent(order::setAssignedAgent);
        }

        List<OrderItem> items = orderItemRepo.findByOrder(rs.getInt("order_id"));
        order.setItems(items);

        order.setSubtotal(rs.getDouble("subtotal"));
        order.setDiscountAmount(rs.getDouble("discount_amount"));
        order.setFinalTotal(rs.getDouble("final_total"));

        return order;
    }
}