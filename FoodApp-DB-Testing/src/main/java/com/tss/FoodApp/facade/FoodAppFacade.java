package com.tss.FoodApp.facade;

import com.tss.FoodApp.command.CommandInvoker;
import com.tss.FoodApp.config.DBConnection;
import com.tss.FoodApp.discount.DiscountContext;
import com.tss.FoodApp.discount.FlatDiscountStrategy;
import com.tss.FoodApp.discount.NoDiscountStrategy;
import com.tss.FoodApp.discount.PercentageDiscountStrategy;
import com.tss.FoodApp.model.Admin;
import com.tss.FoodApp.observer.AgentNotifier;
import com.tss.FoodApp.observer.CustomerNotifier;
import com.tss.FoodApp.observer.OrderEventManager;
import com.tss.FoodApp.payment.PaymentContext;
import com.tss.FoodApp.repository.interfaces.*;
import com.tss.FoodApp.repository.service.*;
import com.tss.FoodApp.service.implementations.*;
import com.tss.FoodApp.service.interfaces.*;
import com.tss.FoodApp.repository.interfaces.*;
import com.tss.FoodApp.repository.service.*;
import com.tss.FoodApp.service.implementations.*;
import com.tss.FoodApp.service.interfaces.*;
import com.tss.FoodApp.singleton.AppConfig;
import com.tss.FoodApp.singleton.RestaurantRegistry;
import com.tss.FoodApp.ui.MainMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FoodAppFacade {
    public void StartApplication(){
        Scanner scanner = new Scanner(System.in);

        try {

            PaymentContext paymentContext = new PaymentContext();
            CommandInvoker invoker = new CommandInvoker();

            IDeliveryAgentRepository agentRepo = new DeliveryAgentRepositoryImpl();
            IDiscountConfigRepo discountRepo = new DiscountConfigRepoImpl();

            IUserRepository userRepo = new UserRepositoryImpl();
            IOrderItemRepository orderItemRepo = new OrderItemRepositoryImpl();
            IMenuItemRepository menuItemRepo = new MenuItemRepositoryImpl();
            IRestaurantRepository restRepo = new RestaurantRepositoryImpl(menuItemRepo,agentRepo);
            IOrderRepository orderRepo = new OrderRepositoryImpl(userRepo,restRepo,orderItemRepo,agentRepo,menuItemRepo);
            IInvoiceRepository invoiceRepository = new InvoiceRepositoryImpl(orderRepo);

            RestaurantRegistry registry =
                    RestaurantRegistry.getInstance(restRepo);

            discountRepo.load();
            DiscountContext discountCtx = buildDiscountContext(AppConfig.getInstance());

            IDiscountService discountService =
                    new DiscountServiceImpl(discountCtx,discountRepo);

            OrderEventManager eventManager = new OrderEventManager();
            eventManager.registerObserver(new CustomerNotifier());
            eventManager.registerObserver(new AgentNotifier());

            IAuthService authService = new AuthServiceImpl(userRepo);
            IMenuService menuService = new MenuServiceImpl(restRepo,menuItemRepo);
            IDeliveryService deliveryService = new DeliveryServiceImpl(restRepo, agentRepo, orderRepo);
            IOrderService orderService = new OrderServiceImpl(
                    orderRepo, restRepo, discountCtx, eventManager, menuItemRepo, agentRepo,deliveryService
            );
            IInvoiceService invoiceService =
                    new InvoiceServiceImpl(orderRepo,invoiceRepository);
            IAgentAuthService agentAuthService = new AgentAuthServiceImpl(restRepo);

            setupAdmin(scanner, userRepo);

            MainMenu mainMenu = new MainMenu(
                    authService,
                    agentAuthService,
                    menuService,
                    orderService,
                    invoiceService,
                    deliveryService,
                    discountCtx,
                    paymentContext,
                    discountService,
                    invoker,
                    registry,
                    restRepo
            );

            mainMenu.show();

        } catch (RuntimeException e) {
            System.out.println("❌ Application failed to start: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected system error occurred.");
        } finally {
            scanner.close();
        }
    }
    private static void setupAdmin(Scanner scanner,
                                   IUserRepository userRepo) {

        boolean adminExists = false;

        String checkSql = "SELECT EXISTS (SELECT 1 FROM admins)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(checkSql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                adminExists = rs.getBoolean(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to check admin existence", e);
        }
        if (adminExists) {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║   🍔  FoodApp                    ║");
            System.out.println("║   Admin account already exists.  ║");
            System.out.println("╚══════════════════════════════════╝\n");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   🍔  Welcome to FoodApp Setup      ║");
        System.out.println("║   First time setup — Create Admin   ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean created = false;

        while (!created) {
            try {

                System.out.println("\n  Please create your Admin account:");
                System.out.println("  ─────────────────────────────────");

                System.out.print("  Admin Name      : ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty())
                    throw new IllegalArgumentException("Name cannot be empty.");

                System.out.print("  Admin Email     : ");
                String email = scanner.nextLine().trim();
                if (email.isEmpty() || !email.contains("@"))
                    throw new IllegalArgumentException("Invalid email.");

                System.out.print("  Admin Password  : ");
                String password = scanner.nextLine();
                if (password.length() < 6)
                    throw new IllegalArgumentException("Password must be at least 6 characters.");

                System.out.print("  Confirm Password: ");
                String confirm = scanner.nextLine();
                if (!confirm.equals(password))
                    throw new IllegalArgumentException("Passwords do not match.");

                System.out.print("  Admin Phone     : ");
                String phone = scanner.nextLine().trim();

                System.out.print("  Admin Code      : ");
                String adminCode = scanner.nextLine().trim();
                if (adminCode.isEmpty())
                    throw new IllegalArgumentException("Admin code cannot be empty.");

                Admin admin = new Admin(100, name, email, password, phone, adminCode);

                userRepo.save(admin);

                System.out.println("\n╔══════════════════════════════════════╗");
                System.out.println("║  ✅ Admin account created!           ║");
                System.out.printf("║  Email   : %-27s║%n", email);
                System.out.println("║  Please login to continue.           ║");
                System.out.println("╚══════════════════════════════════════╝\n");

                created = true;

            } catch (RuntimeException e) {
                System.out.println("❌ " + e.getMessage());
                System.out.println("🔁 Please try again.\n");
            }
        }
    }
    private DiscountContext buildDiscountContext(AppConfig config) {
        DiscountContext ctx = new DiscountContext(new NoDiscountStrategy());

        switch (config.getActiveDiscountType()) {
            case "FLAT" -> ctx.setStrategy(
                    new FlatDiscountStrategy(
                            config.getFlatDiscountAmount(),
                            config.getDiscountThreshold()
                    )
            );
            case "PERCENTAGE" -> ctx.setStrategy(
                    new PercentageDiscountStrategy(
                            config.getDiscountPercentage(),
                            config.getDiscountThreshold()
                    )
            );
            case "NONE" -> ctx.setStrategy(new NoDiscountStrategy());
            default -> ctx.setStrategy(new NoDiscountStrategy());
        }

        return ctx;
    }
}
