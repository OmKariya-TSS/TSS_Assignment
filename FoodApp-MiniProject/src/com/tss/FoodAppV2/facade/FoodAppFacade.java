package com.tss.FoodAppV2.facade;

import com.tss.FoodAppV2.command.CommandInvoker;
import com.tss.FoodAppV2.discount.DiscountContext;
import com.tss.FoodAppV2.discount.FlatDiscountStrategy;
import com.tss.FoodAppV2.model.Admin;
import com.tss.FoodAppV2.observer.AgentNotifier;
import com.tss.FoodAppV2.observer.CustomerNotifier;
import com.tss.FoodAppV2.observer.OrderEventManager;
import com.tss.FoodAppV2.payment.PaymentContext;
import com.tss.FoodAppV2.repository.interfaces.IOrderRepository;
import com.tss.FoodAppV2.repository.interfaces.IRestaurantRepository;
import com.tss.FoodAppV2.repository.interfaces.IUserRepository;
import com.tss.FoodAppV2.repository.service.OrderRepositoryImpl;
import com.tss.FoodAppV2.repository.service.RestaurantRepositoryImpl;
import com.tss.FoodAppV2.repository.service.UserRepositoryImpl;
import com.tss.FoodAppV2.service.implementations.*;
import com.tss.FoodAppV2.service.interfaces.*;
import com.tss.FoodAppV2.singleton.AppConfig;
import com.tss.FoodAppV2.singleton.RestaurantRegistry;
import com.tss.FoodAppV2.ui.MainMenu;

import java.util.Scanner;

public class FoodAppFacade {
    public void StartApplication(){
        Scanner scanner = new Scanner(System.in);

        try {

            AppConfig config = AppConfig.getInstance();
            PaymentContext paymentContext = new PaymentContext();
            CommandInvoker invoker = new CommandInvoker();

            IUserRepository userRepo = new UserRepositoryImpl();
            IRestaurantRepository restRepo = new RestaurantRepositoryImpl();
            IOrderRepository orderRepo = new OrderRepositoryImpl();

            RestaurantRegistry registry =
                    RestaurantRegistry.getInstance(restRepo);

            DiscountContext discountCtx = new DiscountContext(
                    new FlatDiscountStrategy(
                            config.getFlatDiscountAmount(),
                            config.getDiscountThreshold()
                    )
            );

            IDiscountService discountService =
                    new DiscountServiceImpl(discountCtx);

            OrderEventManager eventManager = new OrderEventManager();
            eventManager.registerObserver(new CustomerNotifier());
            eventManager.registerObserver(new AgentNotifier());

            IAuthService authService = new AuthServiceImpl(userRepo);
            IMenuService menuService = new MenuServiceImpl(restRepo);
            IOrderService orderService = new OrderServiceImpl(
                    orderRepo, restRepo, discountCtx, eventManager
            );
            IDeliveryService deliveryService =
                    new DeliveryServiceImpl(restRepo);
            IInvoiceService invoiceService =
                    new InvoiceServiceImpl(orderRepo);
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


        boolean adminExists = userRepo.findAll()
                .stream()
                .anyMatch(u -> u.getRole().name().equals("ADMIN"));

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
}
