package com.tss.FoodApp.ui;

import com.tss.FoodApp.command.CommandInvoker;
import com.tss.FoodApp.discount.DiscountContext;
import com.tss.FoodApp.model.Customer;
import com.tss.FoodApp.payment.PaymentContext;
import com.tss.FoodApp.service.interfaces.*;
import com.tss.FoodApp.service.interfaces.*;
import com.tss.FoodApp.singleton.RestaurantRegistry;

import java.util.Scanner;

public class CustomerPanel implements ConsoleUI {

    private final Customer customer;
    private final InputHelper input;
    private final BrowsePanel browsePanel;
    private final PlaceOrderPanel placeOrderPanel;
    private final MyOrdersPanel myOrdersPanel;
    private final TrackOrderPanel trackOrderPanel;
    private final IAuthService authService;

    public CustomerPanel(Customer customer,
                         IOrderService orderService,
                         IMenuService menuService,
                         IInvoiceService invoiceService,
                         IDeliveryService deliveryService,
                         DiscountContext discountContext,
                         PaymentContext paymentContext,
                         CommandInvoker invoker,
                         RestaurantRegistry registry,
                         IAuthService authService) {

        this.customer = customer;
        this.authService = authService;

        InputHelper sharedInput = new InputHelper(new Scanner(System.in));
        this.input = sharedInput;

        this.browsePanel = new BrowsePanel(registry, menuService);

        PaymentPanel paymentPanel = new PaymentPanel(paymentContext, sharedInput);

        this.placeOrderPanel = new PlaceOrderPanel(
                customer, orderService, menuService, invoiceService,
                deliveryService, discountContext, paymentPanel,
                invoker, registry, browsePanel, sharedInput);

        this.myOrdersPanel = new MyOrdersPanel(
                orderService, invoiceService, invoker, customer, sharedInput, authService);

        this.trackOrderPanel = new TrackOrderPanel(orderService, customer, sharedInput);
    }
    @Override
    public void show() {
        int choice;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     🛒  Customer Panel       ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.println("  Hi, " + customer.getName() + "!");
            System.out.println("  1. Browse Restaurants");
            System.out.println("  2. Place Order");
            System.out.println("  3. View My Orders");
            System.out.println("  4. Track Order Status");
            System.out.println("  5. View Invoice");
            System.out.println("  6. Cancel Order");
            System.out.println("  7. Undo Last Action");
            System.out.println("  8. Logout");
            choice = input.readInt("  Enter choice: ");
            handleInput(choice);
        } while (choice != 8);
    }

    @Override
    public void handleInput(int choice) {
        switch (choice) {
            case 1 -> browsePanel.browseRestaurants();
            case 2 -> placeOrderPanel.buildAndPlaceOrder();
            case 3 -> myOrdersPanel.viewMyOrders();
            case 4 -> trackOrderPanel.trackOrder();
            case 5 -> myOrdersPanel.viewInvoice();
            case 6 -> myOrdersPanel.cancelOrder();
            case 7 -> myOrdersPanel.undoLastAction();
            case 8 -> System.out.println("🔒 Logged out.");
            default -> System.out.println("❌ Invalid choice!");
        }
    }
}