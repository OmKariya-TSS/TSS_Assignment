package com.tss.FoodAppV3.facade;

import com.tss.FoodAppV3.command.CommandInvoker;
import com.tss.FoodAppV3.discount.DiscountContext;
import com.tss.FoodAppV3.discount.FlatDiscountStrategy;
import com.tss.FoodAppV3.discount.NoDiscountStrategy;
import com.tss.FoodAppV3.discount.PercentageDiscountStrategy;
import com.tss.FoodAppV3.enums.OrderStatus;
import com.tss.FoodAppV3.factory.OrderFactory;
import com.tss.FoodAppV3.model.*;
import com.tss.FoodAppV3.observer.AgentNotifier;
import com.tss.FoodAppV3.observer.CustomerNotifier;
import com.tss.FoodAppV3.observer.OrderEventManager;
import com.tss.FoodAppV3.payment.PaymentContext;
import com.tss.FoodAppV3.repository.interfaces.IOrderRepository;
import com.tss.FoodAppV3.repository.interfaces.IRestaurantRepository;
import com.tss.FoodAppV3.repository.interfaces.IUserRepository;
import com.tss.FoodAppV3.repository.service.OrderRepositoryImpl;
import com.tss.FoodAppV3.repository.service.RestaurantRepositoryImpl;
import com.tss.FoodAppV3.repository.service.UserRepositoryImpl;
import com.tss.FoodAppV3.service.implementations.*;
import com.tss.FoodAppV3.service.interfaces.*;
import com.tss.FoodAppV3.singleton.AppConfig;
import com.tss.FoodAppV3.singleton.RestaurantRegistry;
import com.tss.FoodAppV3.ui.*;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class FoodAppFacade {

    public void startApplication() {
        Scanner scanner = new Scanner(System.in);

        try {
            AppConfig.resetInstance();
            AppConfig config = AppConfig.getInstance();

            IUserRepository       userRepo  = new UserRepositoryImpl();
            IRestaurantRepository restRepo  = new RestaurantRepositoryImpl();
            IOrderRepository      orderRepo = new OrderRepositoryImpl();

            syncIdCounters(userRepo, restRepo, orderRepo);
            resetStaleAgents(restRepo, orderRepo);

            RestaurantRegistry.resetInstance();
            RestaurantRegistry registry = RestaurantRegistry.getInstance(restRepo);

            PaymentContext paymentContext = new PaymentContext();
            CommandInvoker invoker        = new CommandInvoker();

            DiscountContext discountCtx = buildDiscountContext(config);

            OrderEventManager eventManager = new OrderEventManager();
            eventManager.registerObserver(new CustomerNotifier());
            eventManager.registerObserver(new AgentNotifier());

            IAuthService      authService      = new AuthServiceImpl(userRepo);
            IMenuService      menuService      = new MenuServiceImpl(restRepo);
            IDiscountService  discountService  = new DiscountServiceImpl(discountCtx);
            IDeliveryService  deliveryService  = new DeliveryServiceImpl(restRepo);
            IInvoiceService   invoiceService   = new InvoiceServiceImpl(orderRepo);
            IAgentAuthService agentAuthService = new AgentAuthServiceImpl(restRepo);
            IOrderService     orderService     = new OrderServiceImpl(
                    orderRepo, restRepo, discountCtx, eventManager
            );

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


    private void syncIdCounters(IUserRepository userRepo,
                                IRestaurantRepository restRepo,
                                IOrderRepository orderRepo) {

        MainMenu.users = userRepo.findAll().stream()
                .mapToInt(User::getUserId)
                .max().orElse(0) + 1;

        RestaurantPanel.restaurantCnt = restRepo.findAll().stream()
                .mapToInt(Restaurant::getRestaurantId)
                .max().orElse(0) + 1;

        MenuPanel.menuItemCount = restRepo.findAll().stream()
                .flatMap(r -> r.getMenu().stream())
                .mapToInt(MenuItem::getItemId)
                .max().orElse(0) + 1;

        DeliveryPanel.deliveryAgentCount = restRepo.findAll().stream()
                .flatMap(r -> r.getAgents().stream())
                .mapToInt(DeliveryAgent::getAgentId)
                .max().orElse(0) + 1;
        int maxOrderId = orderRepo.findAll().stream()
                .mapToInt(Order::getOrderId)
                .max().orElse(0) + 1;
        OrderFactory.setIdCounter(maxOrderId);
    }


    private static void setupAdmin(Scanner scanner, IUserRepository userRepo) {

        boolean adminExists = userRepo.findAll().stream()
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
                    throw new IllegalArgumentException(
                            "Password must be at least 6 characters.");

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

                int adminId = MainMenu.users++;
                Admin admin = new Admin(adminId, name, email, password, phone, adminCode);
                userRepo.save(admin);

                System.out.println("\n╔══════════════════════════════════════╗");
                System.out.println("║  ✅ Admin account created!           ║");
                System.out.printf( "║  Email   : %-27s║%n", email);
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
        return switch (config.getActiveDiscountType()) {

            case "PERCENTAGE" -> new DiscountContext(
                    new PercentageDiscountStrategy(
                            config.getDiscountPercentage(),
                            config.getDiscountThreshold()
                    )
            );

            case "NONE" -> new DiscountContext(
                    new NoDiscountStrategy()
            );

            default -> new DiscountContext(
                    new FlatDiscountStrategy(
                            config.getFlatDiscountAmount(),
                            config.getDiscountThreshold()
                    )
            );
        };
    }
    private void resetStaleAgents(IRestaurantRepository restRepo,
                                  IOrderRepository orderRepo) {

        Set<Integer> legitimatelyBusy = orderRepo.findAll().stream()
                .filter(o -> o.getStatus() == OrderStatus.OUT_FOR_DELIVERY
                        || o.getStatus() == OrderStatus.CONFIRMED
                        || o.getStatus() == OrderStatus.PREPARING
                        || o.getStatus() == OrderStatus.PLACED)
                .filter(o -> o.getAssignedAgent() != null)
                .map(o -> o.getAssignedAgent().getAgentId())
                .collect(Collectors.toSet());

        restRepo.findAll().forEach(restaurant -> {
            boolean changed = false;

            for (DeliveryAgent agent : restaurant.getAgents()) {
                if (!agent.isAvailable()
                        && !legitimatelyBusy.contains(agent.getAgentId())) {
                    agent.markAvailable();
                    changed = true;
                    System.out.println("🔄 Freed stale agent: " + agent.getName());
                }
            }

            if (changed) restRepo.update(restaurant);
        });
    }
}
