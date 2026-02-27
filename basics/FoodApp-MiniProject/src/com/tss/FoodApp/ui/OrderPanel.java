package com.tss.FoodApp.ui;


import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.exceptions.*;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.service.interfaces.IDiscountService;
import com.tss.FoodApp.service.interfaces.IOrderService;

import java.util.List;

public class OrderPanel {

    private final IOrderService orderService;
    private final IDiscountService discountService;
    private final InputHelper input;

    public OrderPanel(IOrderService orderService, IDiscountService discountService, InputHelper input) {
        this.orderService    = orderService;
        this.discountService = discountService;
        this.input           = input;
    }

    // ─── Orders ────────────────────────────────────────────────────────────────

    public void viewAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            if (orders.isEmpty()) {
                System.out.println("⚠ No orders yet.");
                return;
            }
            System.out.println("\n--- 📋 All Orders ---");
            orders.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    public void filterOrdersByStatus() {
        try {
            System.out.println("\n  PLACED | CONFIRMED | PREPARING | OUT_FOR_DELIVERY | DELIVERED | CANCELLED");
            System.out.print("  Enter status: ");
            String inputStr = input.getScanner().nextLine().toUpperCase().trim();

            OrderStatus status;
            try {
                status = OrderStatus.valueOf(inputStr);
            } catch (IllegalArgumentException e) {
                throw new InvalidOrderStateException("'" + inputStr + "' is not a valid order status.");
            }

            List<Order> filtered = orderService.getAllOrders()
                    .stream()
                    .filter(o -> o.getStatus() == status)
                    .toList();

            if (filtered.isEmpty()) {
                System.out.println("⚠ No orders with status: " + status);
            } else {
                System.out.println("\n--- Orders: " + status + " ---");
                filtered.forEach(System.out::println);
            }

        } catch (InvalidOrderStateException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    public void manageOrderStatus() {
        try {
            List<Order> orders = orderService.getAllOrders();
            if (orders.isEmpty()) {
                System.out.println("⚠ No orders yet.");
                return;
            }

            System.out.println("\n--- 🔄 Manage Order Status ---");
            orders.forEach(o -> System.out.printf(
                    "  Order #%-3d | %-20s | Status: %s%n",
                    o.getOrderId(), o.getRestaurant().getName(), o.getStatus()
            ));

            int orderId = input.readInt("\n  Enter Order ID: ");

            Order order = orderService.getOrderById(orderId);
            if (order == null) {
                throw new OrderNotFoundException("Order not found with ID: " + orderId);
            }

            System.out.println("  Current Status : " + order.getStatus());
            if (!order.getStatus().isFinal()) {
                System.out.println("  Next Status    : " + order.getStatus().next());
            }
            System.out.println();
            System.out.println("  1. Advance to next status");
            System.out.println("  2. Mark as DELIVERED");
            System.out.println("  3. Set specific status manually");
            System.out.println("  4. Back");
            int choice = input.readInt("  Choose: ");

            switch (choice) {
                case 1 -> {
                    if (order.getStatus().isFinal()) {
                        throw new InvalidOrderOperationException(
                                "Cannot advance a " + order.getStatus() + " order.");
                    }
                    orderService.advanceOrderStatus(orderId);
                }
                case 2 -> orderService.markDelivered(orderId);
                case 3 -> setStatusManually(orderId);
                case 4 -> System.out.println("↩ Back.");
                default -> System.out.println("❌ Invalid choice.");
            }

        } catch (OrderNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (InvalidOrderOperationException e) {
            System.out.println("  ❌ Operation error: " + e.getMessage());
        } catch (InvalidOrderStateException e) {
            System.out.println("  ❌ State error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    private void setStatusManually(int orderId) {
        try {
            System.out.println("\n  1.PLACED  2.CONFIRMED  3.PREPARING");
            System.out.println("  4.OUT_FOR_DELIVERY  5.DELIVERED  6.CANCELLED");
            int choice = input.readInt("  Choose: ");

            OrderStatus status = switch (choice) {
                case 1 -> OrderStatus.PLACED;
                case 2 -> OrderStatus.CONFIRMED;
                case 3 -> OrderStatus.PREPARING;
                case 4 -> OrderStatus.OUT_FOR_DELIVERY;
                case 5 -> OrderStatus.DELIVERED;
                case 6 -> OrderStatus.CANCELLED;
                default -> throw new InvalidOrderStateException("Invalid status choice: " + choice);
            };

            orderService.updateOrderStatus(orderId, status);

        } catch (InvalidOrderStateException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (OrderNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (InvalidOrderOperationException e) {
            System.out.println("  ❌ Operation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    // ─── Discounts ─────────────────────────────────────────────────────────────

    public void manageDiscountSettings() {
        int choice;
        do {
            try {
                System.out.println("\n╔══════════════════════════════╗");
                System.out.println("║    💰 Discount Settings      ║");
                System.out.println("╚══════════════════════════════╝");
                discountService.showCurrentConfig();
                System.out.println();
                System.out.println("  1. Set Flat Discount");
                System.out.println("  2. Set Percentage Discount");
                System.out.println("  3. Disable All Discounts");
                System.out.println("  4. Update Minimum Order Threshold Only");
                System.out.println("  5. Back");
                choice = input.readInt("  Enter choice: ");

                switch (choice) {
                    case 1 -> setFlatDiscount();
                    case 2 -> setPercentageDiscount();
                    case 3 -> discountService.setNoDiscount();
                    case 4 -> updateThresholdOnly();
                    case 5 -> System.out.println("↩ Back to Admin Panel.");
                    default -> System.out.println("❌ Invalid choice!");
                }

            } catch (InvalidDiscountException e) {
                System.out.println("  ❌ Discount error: " + e.getMessage());
                choice = 0;
            } catch (Exception e) {
                System.out.println("  ❌ Unexpected error: " + e.getMessage());
                choice = 0;
            }
        } while (choice != 5);
    }

    private void setFlatDiscount() {
        try {
            double amount    = input.readDouble("  Flat discount amount ₹: ");
            double threshold = input.readDouble("  Minimum order value  ₹: ");

            if (amount <= 0) {
                throw new InvalidDiscountException("Flat discount amount must be greater than 0.");
            }
            if (threshold <= 0) {
                throw new InvalidDiscountException("Minimum order threshold must be greater than 0.");
            }
            if (amount >= threshold) {
                throw new InvalidDiscountException(
                        "Discount amount (₹" + amount + ") cannot be >= threshold (₹" + threshold + ").");
            }
            discountService.setFlatDiscount(amount, threshold);

        } catch (InvalidDiscountException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    private void setPercentageDiscount() {
        try {
            double percentage = input.readDouble("  Discount percentage (%): ");
            double threshold  = input.readDouble("  Minimum order value  ₹: ");

            if (percentage <= 0 || percentage > 100) {
                throw new InvalidDiscountException("Percentage must be between 1 and 100.");
            }
            if (threshold <= 0) {
                throw new InvalidDiscountException("Minimum order threshold must be greater than 0.");
            }
            discountService.setPercentageDiscount(percentage, threshold);

        } catch (InvalidDiscountException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    private void updateThresholdOnly() {
        try {
            double threshold = input.readDouble("  New minimum order value ₹: ");

            if (threshold <= 0) {
                throw new InvalidDiscountException("Threshold must be greater than 0.");
            }
            discountService.updateThreshold(threshold);

        } catch (InvalidDiscountException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }
}