package com.tss.FoodApp.ui;

import com.tss.FoodApp.command.CommandInvoker;
import com.tss.FoodApp.discount.DiscountContext;
import com.tss.FoodApp.enums.UserRole;
import com.tss.FoodApp.model.Admin;
import com.tss.FoodApp.model.Customer;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.User;
import com.tss.FoodApp.payment.PaymentContext;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.service.interfaces.*;
import com.tss.FoodApp.service.interfaces.*;
import com.tss.FoodApp.singleton.RestaurantRegistry;

import java.util.Scanner;

public class MainMenu implements ConsoleUI {

    static int users = 1;

    private final IAuthService       authService;
    private final IAgentAuthService agentAuthService;
    private final IMenuService menuService;
    private final IOrderService orderService;
    private final IDeliveryService deliveryService;
    private final IInvoiceService    invoiceService;
    private final DiscountContext    discountContext;
    private final PaymentContext     paymentContext;
    private final CommandInvoker     invoker;
    private final IDiscountService discountService;
    private final RestaurantRegistry registry;
    private final IRestaurantRepository restaurantRepo;


    private final Scanner    scanner     = new Scanner(System.in);
    private final InputHelper inputHelper = new InputHelper(scanner);

    private AdminPanel    adminPanel;
    private CustomerPanel customerPanel;

    public MainMenu(IAuthService authService,
                    IAgentAuthService agentAuthService,
                    IMenuService menuService,
                    IOrderService orderService,
                    IInvoiceService invoiceService,
                    IDeliveryService deliveryService,
                    DiscountContext discountContext,
                    PaymentContext paymentContext,
                    IDiscountService discountService,
                    CommandInvoker invoker,
                    RestaurantRegistry registry,
                    IRestaurantRepository restaurantRepo) {
        this.authService      = authService;
        this.agentAuthService = agentAuthService;
        this.menuService      = menuService;
        this.orderService     = orderService;
        this.invoiceService   = invoiceService;
        this.deliveryService  = deliveryService;
        this.discountContext  = discountContext;
        this.paymentContext   = paymentContext;
        this.discountService  = discountService;
        this.invoker          = invoker;
        this.registry         = registry;
        this.restaurantRepo=restaurantRepo;
    }

    @Override
    public void show() {
        int choice = -1;

        do {
            try {
                System.out.println("\n╔══════════════════════════════╗");
                System.out.println("║   🍔  Welcome to FoodApp    ║");
                System.out.println("╚══════════════════════════════╝");
                System.out.println("  1. Login (Customer / Admin)");
                System.out.println("  2. Login as Delivery Agent");
                System.out.println("  3. Register as Customer");
                System.out.println("  4. Exit");
                System.out.print("  Enter choice: ");

                choice = scanner.nextInt();
                scanner.nextLine();
                handleInput(choice);

            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }

        } while (choice != 4);
    }

    @Override
    public void handleInput(int choice) {
        switch (choice) {
            case 1 -> loginFlow();
            case 2 -> agentLoginFlow();
            case 3 -> registerFlow();
            case 4 -> System.out.println("👋 Thank you for visiting FoodApp!");
            default -> System.out.println("❌ Invalid choice, try again!");
        }
    }


    private void loginFlow() {
        try {
            System.out.print("  Enter email    : ");
            String email = scanner.nextLine();

            System.out.print("  Enter password : ");
            String password = scanner.nextLine();

            if (email.isBlank() || password.isBlank())
                throw new IllegalArgumentException("Email and password cannot be empty.");

            User user = authService.login(email, password);

            if (user == null)
                throw new IllegalStateException("Invalid credentials.");

            System.out.println("✅ Welcome, " + user.getName() + "!");
            routeToDashboard(user);

        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("❌ Login failed: " + e.getMessage());
        }
    }


    private void agentLoginFlow() {
        try {
            System.out.println("\n--- 🚴 Delivery Agent Login ---");
            System.out.print("  Enter phone    : ");
            String phone = scanner.nextLine();

            System.out.print("  Enter password : ");
            String password = scanner.nextLine();

            if (phone.isBlank() || password.isBlank())
                throw new IllegalArgumentException("Phone and password cannot be empty.");

            DeliveryAgent agent = agentAuthService.login(phone, password);

            System.out.println("✅ Welcome, Agent " + agent.getName() + "!");

            AgentPanel agentPanel = new AgentPanel(
                    agent,
                    orderService,
                    agentAuthService,
                    restaurantRepo,
                    inputHelper
            );
            agentPanel.show();

        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("❌ Agent login failed: " + e.getMessage());
        }
    }


    private void registerFlow() {
        try {
            System.out.println("\n--- 📝 Customer Registration ---");

            System.out.print("  Name     : ");
            String name = scanner.nextLine();

            System.out.print("  Email    : ");
            String email = scanner.nextLine();

            System.out.print("  Password : ");
            String password = scanner.nextLine();

            System.out.print("  Phone    : ");
            String phone = scanner.nextLine();

            System.out.print("  Address  : ");
            String address = scanner.nextLine();

            if (name.isBlank() || email.isBlank() || password.isBlank())
                throw new IllegalArgumentException("Required fields cannot be empty.");

            Customer customer =
                    new Customer(users++, name, email, password, phone, address);

            boolean success = authService.register(customer);

            if (success) System.out.println("✅ Registered! Please login.");
            else         System.out.println("❌ Email already exists.");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("❌ Registration failed: " + e.getMessage());
        }
    }

    private void routeToDashboard(User user) {
        if (user == null) {
            System.out.println("❌ User session invalid.");
            return;
        }

        UserRole role = user.getRole();

        try {
            if (role == UserRole.ADMIN) {
                if (adminPanel == null) {
                    adminPanel = new AdminPanel(
                            (Admin) user,
                            menuService,
                            orderService,
                            deliveryService,
                            discountService,
                            registry
                    );
                }
                adminPanel.show();

            } else if (role == UserRole.CUSTOMER) {
                customerPanel = new CustomerPanel(
                        (Customer) user,
                        orderService,
                        menuService,
                        invoiceService,
                        deliveryService,
                        discountContext,
                        paymentContext,
                        invoker,
                        registry,
                        authService
                );
                customerPanel.show();

            } else {
                System.out.println("❌ Unknown role: " + role);
            }

        } catch (RuntimeException e) {
            System.out.println("❌ Error loading dashboard: " + e.getMessage());
        }
    }
}