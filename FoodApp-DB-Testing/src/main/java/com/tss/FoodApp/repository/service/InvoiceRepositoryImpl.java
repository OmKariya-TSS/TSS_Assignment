package com.tss.FoodApp.repository.service;

import com.tss.FoodApp.config.DBConnection;
import com.tss.FoodApp.model.Invoice;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.repository.interfaces.IInvoiceRepository;
import com.tss.FoodApp.repository.interfaces.IOrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceRepositoryImpl implements IInvoiceRepository {

    private final IOrderRepository orderRepo;

    public InvoiceRepositoryImpl(IOrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void save(Invoice invoice) {

        if (invoice == null) {
            throw new IllegalArgumentException("Invoice cannot be null.");
        }

        if (invoice.getOrder() == null || invoice.getOrder().getOrderId() <= 0) {
            throw new IllegalArgumentException("Invoice must have a valid order.");
        }

        String sql = """
            INSERT INTO invoices
            (order_id, subtotal, discount_applied, final_amount, payment_method, generated_at)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"invoice_id"})) {

            ps.setInt(1, invoice.getOrder().getOrderId());
            ps.setDouble(2, invoice.getSubtotal());
            ps.setDouble(3, invoice.getDiscountApplied());
            ps.setDouble(4, invoice.getFinalAmount());
            ps.setString(5, invoice.getPaymentMethod());
            ps.setTimestamp(6, Timestamp.valueOf(invoice.getGeneratedAt()));

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Failed to insert invoice, no rows affected.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    invoice.setInvoiceId(rs.getInt(1));
                } else {
                    throw new RuntimeException("Failed to retrieve generated invoice ID.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save invoice", e);
        }
    }
    @Override
    public Optional<Invoice> findByOrderId(int orderId) {

        if (orderId <= 0) throw new IllegalArgumentException("Order ID must be positive.");

        String sql = """
        SELECT invoice_id, order_id, subtotal, discount_applied,
               final_amount, payment_method, generated_at
        FROM invoices
        WHERE order_id = ?
        """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch invoice for order ID: " + orderId, e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Invoice> findById(int invoiceId) {

        if (invoiceId <= 0) {
            throw new IllegalArgumentException("Invoice ID must be positive.");
        }

        String sql = """
            SELECT invoice_id, order_id, subtotal, discount_applied,
                   final_amount, payment_method, generated_at
            FROM invoices
            WHERE invoice_id = ?
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, invoiceId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch invoice with ID: " + invoiceId, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Invoice> findAll() {

        List<Invoice> invoices = new ArrayList<>();

        String sql = """
            SELECT invoice_id, order_id, subtotal, discount_applied,
                   final_amount, payment_method, generated_at
            FROM invoices
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                invoices.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all invoices", e);
        }

        return invoices;
    }

    @Override
    public List<Invoice> findByCustomer(int customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID must be positive.");
        }

        List<Invoice> invoices = new ArrayList<>();

        String sql = """
            SELECT i.invoice_id, i.order_id, i.subtotal, i.discount_applied,
                   i.final_amount, i.payment_method, i.generated_at
            FROM invoices i
            JOIN orders o ON i.order_id = o.order_id
            WHERE o.customer_id = ?
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    invoices.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch invoices for customer ID: " + customerId, e);
        }

        return invoices;
    }

    @Override
    public List<Invoice> findByRestaurant(int restaurantId) {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Restaurant ID must be positive.");
        }

        List<Invoice> invoices = new ArrayList<>();

        String sql = """
            SELECT i.invoice_id, i.order_id, i.subtotal, i.discount_applied,
                   i.final_amount, i.payment_method, i.generated_at
            FROM invoices i
            JOIN orders o ON i.order_id = o.order_id
            WHERE o.restaurant_id = ?
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    invoices.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch invoices for restaurant ID: " + restaurantId, e);
        }

        return invoices;
    }

    @Override
    public void update(Invoice invoice) {

        if (invoice == null) {
            throw new IllegalArgumentException("Invoice cannot be null.");
        }

        if (invoice.getInvoiceId() <= 0) {
            throw new IllegalArgumentException("Invoice ID must be positive.");
        }

        String sql = """
            UPDATE invoices
            SET subtotal = ?,
                discount_applied = ?,
                final_amount = ?,
                payment_method = ?,
                generated_at = ?
            WHERE invoice_id = ?
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, invoice.getSubtotal());
            ps.setDouble(2, invoice.getDiscountApplied());
            ps.setDouble(3, invoice.getFinalAmount());
            ps.setString(4, invoice.getPaymentMethod());
            ps.setTimestamp(5, Timestamp.valueOf(invoice.getGeneratedAt()));
            ps.setInt(6, invoice.getInvoiceId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Invoice not found with ID: " + invoice.getInvoiceId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update invoice with ID: " + invoice.getInvoiceId(), e);
        }
    }

    @Override
    public void delete(int invoiceId) {

        if (invoiceId <= 0) {
            throw new IllegalArgumentException("Invoice ID must be positive.");
        }

        String sql = "DELETE FROM invoices WHERE invoice_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, invoiceId);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Invoice not found with ID: " + invoiceId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete invoice with ID: " + invoiceId, e);
        }
    }

    private Invoice mapRow(ResultSet rs) throws SQLException {

        int invoiceId = rs.getInt("invoice_id");
        int orderId = rs.getInt("order_id");

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException(
                        "Order not found for invoice, order ID: " + orderId));

        Invoice invoice = new Invoice(invoiceId, order);
        invoice.setSubtotal(rs.getDouble("subtotal"));
        invoice.setDiscountApplied(rs.getDouble("discount_applied"));
        invoice.setFinalAmount(rs.getDouble("final_amount"));
        invoice.setPaymentMethod(rs.getString("payment_method"));
        invoice.setGeneratedAt(rs.getTimestamp("generated_at").toLocalDateTime());

        return invoice;
    }
}