import com.tss.FoodApp.command.CommandInvoker;
import com.tss.FoodApp.discount.DiscountContext;
import com.tss.FoodApp.discount.FlatDiscountStrategy;
import com.tss.FoodApp.model.Admin;
import com.tss.FoodApp.observer.AgentNotifier;
import com.tss.FoodApp.observer.CustomerNotifier;
import com.tss.FoodApp.observer.OrderEventManager;
import com.tss.FoodApp.payment.PaymentContext;
import com.tss.FoodApp.repository.interfaces.*;
import com.tss.FoodApp.repository.service.OrderRepositoryImpl;
import com.tss.FoodApp.repository.service.RestaurantRepositoryImpl;
import com.tss.FoodApp.repository.service.UserRepositoryImpl;
import com.tss.FoodApp.service.implementations.*;
import com.tss.FoodApp.service.interfaces.*;
import com.tss.FoodApp.singleton.AppConfig;
import com.tss.FoodApp.singleton.RestaurantRegistry;
import com.tss.FoodApp.ui.MainMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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

            setupAdmin(scanner, userRepo);

            MainMenu mainMenu = new MainMenu(
                    authService,
                    menuService,
                    orderService,
                    invoiceService,
                    deliveryService,
                    discountCtx,
                    paymentContext,
                    discountService,
                    invoker,
                    registry
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