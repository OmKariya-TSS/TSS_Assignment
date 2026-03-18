package com.tss.FoodApp.ui;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.service.interfaces.IAgentAuthService;
import com.tss.FoodApp.service.interfaces.IOrderService;

import java.util.List;

public class AgentPanel implements ConsoleUI {

    private final DeliveryAgent agent;
    private final IOrderService orderService;
    private final IAgentAuthService agentAuthService;
    private final IRestaurantRepository restaurantRepo;
    private final InputHelper input;

    public AgentPanel(DeliveryAgent agent,
                      IOrderService orderService,
                      IAgentAuthService agentAuthService,
                      IRestaurantRepository restaurantRepo,
                      InputHelper input) {
        this.agent            = agent;
        this.orderService     = orderService;
        this.agentAuthService = agentAuthService;
        this.restaurantRepo   = restaurantRepo;
        this.input            = input;
    }

    @Override
    public void show() {
        int choice;
        do {
            DeliveryAgent fresh = getFreshAgent();

            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     🚴 Agent Dashboard       ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.println("  Welcome, " + fresh.getName());
            System.out.println("  Status : " + (fresh.isAvailable()
                    ? "✅ Available" : "🔴 Busy"));
            System.out.println("  ─────────────────────────────");
            System.out.println("  1. View My Assigned Order");
            System.out.println("  2. Mark Order as Delivered");
            System.out.println("  3. View My Stats");
            System.out.println("  4. Logout");

            choice = input.readInt("  Enter choice: ");
            handleInput(choice);

        } while (choice != 4);
    }

    @Override
    public void handleInput(int choice) {
        switch (choice) {
            case 1 -> viewAssignedOrder();
            case 2 -> markDelivered();
            case 3 -> viewStats();
            case 4 -> {
                agentAuthService.logout();
                System.out.println("🔒 Logged out.");
            }
            default -> System.out.println("❌ Invalid choice!");
        }
    }

    private void viewAssignedOrder() {
        try {
            List<Order> assigned = getMyActiveOrders();

            if (assigned.isEmpty()) {
                System.out.println("  ℹ No active deliveries assigned to you.");
                return;
            }

            System.out.println("\n--- 📦 Your Assigned Order(s) ---");
            assigned.forEach(o -> {
                System.out.println("  Order #"      + o.getOrderId());
                System.out.println("  Customer  : " + o.getCustomer().getName());
                System.out.println("  Address   : " + o.getCustomer().getAddress());
                System.out.println("  Restaurant: " + o.getRestaurant().getName());
                System.out.println("  Total     : ₹" + o.getFinalTotal());
                System.out.println("  Items     : " + o.getItems().size());
                System.out.println("  ─────────────────────────────");
            });

        } catch (Exception e) {
            System.out.println("  ❌ Error: " + e.getMessage());
        }
    }

    private void markDelivered() {
        try {
            List<Order> active = getMyActiveOrders();

            if (active.isEmpty()) {
                System.out.println("  ℹ No active orders to deliver.");
                return;
            }

            System.out.println("\n--- Active Orders ---");
            active.forEach(o -> System.out.printf(
                    "  Order #%-3d | Customer: %-15s | ₹%.2f%n",
                    o.getOrderId(),
                    o.getCustomer().getName(),
                    o.getFinalTotal()
            ));

            int orderId = input.readInt("  Enter Order ID to mark delivered: ");

            boolean isYours = active.stream()
                    .anyMatch(o -> o.getOrderId() == orderId);

            if (!isYours) {
                System.out.println("  ❌ Order #" + orderId
                        + " is not assigned to you.");
                return;
            }

            orderService.markDelivered(orderId);
            System.out.println("  ✅ Order #" + orderId + " marked as delivered!");

        } catch (Exception e) {
            System.out.println("  ❌ Error: " + e.getMessage());
        }
    }

    private void viewStats() {
        DeliveryAgent fresh = getFreshAgent();

        System.out.println("\n--- 📊 Your Stats ---");
        System.out.println("  Name             : " + fresh.getName());
        System.out.println("  Phone            : " + fresh.getPhone());
        System.out.println("  Total Deliveries : " + fresh.getTotalDeliveries());
        System.out.println("  Current Status   : "
                + (fresh.isAvailable() ? "✅ Available" : "🔴 Out for Delivery"));
    }

    private DeliveryAgent getFreshAgent() {
        return restaurantRepo.findAll().stream()
                .flatMap(r -> r.getAgents().stream())
                .filter(a -> a.getAgentId() == agent.getAgentId())
                .findFirst()
                .orElse(agent);
    }
    private List<Order> getMyActiveOrders() {
        return orderService.getAllOrders().stream()
                .filter(o -> o.getAssignedAgent() != null
                        && o.getAssignedAgent().getAgentId() == agent.getAgentId()
                        && o.getStatus() == OrderStatus.OUT_FOR_DELIVERY)
                .toList();
    }
}