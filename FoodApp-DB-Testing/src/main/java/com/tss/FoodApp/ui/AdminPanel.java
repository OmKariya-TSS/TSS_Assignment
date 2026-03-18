package com.tss.FoodApp.ui;

import com.tss.FoodApp.model.Admin;
import com.tss.FoodApp.service.interfaces.IDeliveryService;
import com.tss.FoodApp.service.interfaces.IDiscountService;
import com.tss.FoodApp.service.interfaces.IMenuService;
import com.tss.FoodApp.service.interfaces.IOrderService;
import com.tss.FoodApp.singleton.RestaurantRegistry;

import java.util.Scanner;

public class AdminPanel implements ConsoleUI {

    private final Admin admin;
    private final InputHelper input;
    private final RestaurantPanel restaurantPanel;
    private final MenuPanel menuPanel;
    private final DeliveryPanel deliveryPanel;
    private final OrderPanel orderPanel;

    public AdminPanel(Admin admin,
                      IMenuService menuService,
                      IOrderService orderService,
                      IDeliveryService deliveryService,
                      IDiscountService discountService,
                      RestaurantRegistry registry) {
        this.admin = admin;

        InputHelper sharedInput = new InputHelper(new Scanner(System.in));
        this.input = sharedInput;

        this.restaurantPanel = new RestaurantPanel(registry, sharedInput);
        this.menuPanel       = new MenuPanel(menuService, registry, sharedInput);
        this.deliveryPanel   = new DeliveryPanel(deliveryService, registry, sharedInput);
        this.orderPanel      = new OrderPanel(orderService, discountService, sharedInput);
    }

    @Override
    public void show() {
        int choice;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       🛠  Admin Panel        ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.println("  1.  Add Restaurant");
            System.out.println("  2.  Remove Restaurant");
            System.out.println("  3.  View All Restaurants");
            System.out.println("  4.  Manage Menu for a Restaurant");
            System.out.println("  5.  Add Delivery Agent");
            System.out.println("  6.  View Delivery Agents");
            System.out.println("  7.  View All Orders");
            System.out.println("  8.  Filter Orders by Status");
            System.out.println("  9.  Manage Order Status");
            System.out.println("  10. Manage Discount Settings");
            System.out.println("  11. Logout");
            choice = input.readInt("  Enter choice: ");
            handleInput(choice);
        } while (choice != 11);
    }

    @Override
    public void handleInput(int choice) {
        switch (choice) {
            case 1  -> restaurantPanel.addRestaurant();
            case 2  -> restaurantPanel.removeRestaurant();
            case 3  -> restaurantPanel.viewAllRestaurants();
            case 4  -> menuPanel.manageMenuForRestaurant();
            case 5  -> deliveryPanel.addDeliveryAgent();
            case 6  -> deliveryPanel.viewDeliveryAgents();
            case 7  -> orderPanel.viewAllOrders();
            case 8  -> orderPanel.filterOrdersByStatus();
            case 9  -> orderPanel.manageOrderStatus();
            case 10 -> orderPanel.manageDiscountSettings();
            case 11 -> System.out.println("🔒 Logged out.");
            default -> System.out.println("❌ Invalid choice!");
        }
    }
}