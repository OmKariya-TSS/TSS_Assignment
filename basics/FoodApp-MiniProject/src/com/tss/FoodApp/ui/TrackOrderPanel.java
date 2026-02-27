package com.tss.FoodApp.ui;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.model.Customer;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.service.interfaces.IOrderService;

import java.util.List;

public class TrackOrderPanel {

    private final IOrderService orderService;
    private final Customer customer;
    private final InputHelper input;

    public TrackOrderPanel(IOrderService orderService, Customer customer, InputHelper input) {
        this.orderService = orderService;
        this.customer     = customer;
        this.input        = input;
    }

    public void trackOrder() {
        List<Order> orders = orderService.getOrdersByCustomer(customer.getUserId());
        if (orders.isEmpty()) {
            System.out.println("⚠ No orders to track.");
            return;
        }

        System.out.println("\n--- 📍 Track Order ---");
        orders.forEach(o -> System.out.printf(
                "  Order #%-3d | %-20s | %-15s | Agent: %s%n",
                o.getOrderId(),
                o.getRestaurant().getName(),
                o.getStatus(),
                o.getAssignedAgent() != null
                        ? o.getAssignedAgent().getName()
                        : "Not assigned"
        ));

        int orderId = input.readInt("\n  Enter Order ID for details: ");

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            System.out.println("❌ Order not found.");
            return;
        }

        System.out.println("\n  ── Order #" + orderId + " Timeline ──");
        printStatusTimeline(order.getStatus());
        System.out.println("\n  Restaurant : " + order.getRestaurant().getName());
        System.out.println("  Items      : " + order.getItems().size());
        System.out.println("  Total      : ₹" + order.getFinalTotal());
        System.out.println("  Agent      : " + (order.getAssignedAgent() != null
                ? order.getAssignedAgent().getName() + " (" + order.getAssignedAgent().getPhone() + ")"
                : "Not assigned yet"));
    }

    private void printStatusTimeline(OrderStatus current) {
        OrderStatus[] pipeline = {
                OrderStatus.PLACED,
                OrderStatus.CONFIRMED,
                OrderStatus.PREPARING,
                OrderStatus.OUT_FOR_DELIVERY,
                OrderStatus.DELIVERED
        };

        for (OrderStatus s : pipeline) {
            if (s == current) {
                System.out.println("  ▶ [" + s + "] ← You are here");
            } else if (s.ordinal() < current.ordinal()) {
                System.out.println("  ✅  " + s);
            } else {
                System.out.println("  ⬜  " + s);
            }
        }
    }
}