package com.tss.FoodApp.ui;


import com.tss.FoodApp.builder.OrderBuilder;
import com.tss.FoodApp.command.CommandInvoker;
import com.tss.FoodApp.command.PlaceOrderCommand;
import com.tss.FoodApp.discount.DiscountContext;
import com.tss.FoodApp.model.*;
import com.tss.FoodApp.service.interfaces.IDeliveryService;
import com.tss.FoodApp.service.interfaces.IInvoiceService;
import com.tss.FoodApp.service.interfaces.IMenuService;
import com.tss.FoodApp.service.interfaces.IOrderService;
import com.tss.FoodApp.singleton.RestaurantRegistry;

import java.util.Optional;

public class PlaceOrderPanel {

    private final Customer customer;
    private final IOrderService orderService;
    private final IMenuService menuService;
    private final IInvoiceService invoiceService;
    private final IDeliveryService deliveryService;
    private final DiscountContext discountContext;
    private final PaymentPanel paymentPanel;
    private final CommandInvoker invoker;
    private final RestaurantRegistry registry;
    private final BrowsePanel browsePanel;
    private final InputHelper input;

    public PlaceOrderPanel(Customer customer,
                           IOrderService orderService,
                           IMenuService menuService,
                           IInvoiceService invoiceService,
                           IDeliveryService deliveryService,
                           DiscountContext discountContext,
                           PaymentPanel paymentPanel,
                           CommandInvoker invoker,
                           RestaurantRegistry registry,
                           BrowsePanel browsePanel,
                           InputHelper input) {
        this.customer        = customer;
        this.orderService    = orderService;
        this.menuService     = menuService;
        this.invoiceService  = invoiceService;
        this.deliveryService = deliveryService;
        this.discountContext = discountContext;
        this.paymentPanel    = paymentPanel;
        this.invoker         = invoker;
        this.registry        = registry;
        this.browsePanel     = browsePanel;
        this.input           = input;
    }

    public void buildAndPlaceOrder() {
        try {
            registry.displayAll();
            int resId = input.readInt("  Enter restaurant ID: ");

            Restaurant restaurant = registry.getById(resId)
                    .orElseThrow(() -> new RuntimeException("Restaurant not found ID: " + resId));

            browsePanel.viewMenuForRestaurant(resId);

            OrderBuilder builder  = new OrderBuilder(customer, restaurant);
            boolean itemAdded = false;

            while (true) {
                int itemId = input.readInt("  Enter item ID to add (0 to finish): ");
                if (itemId == 0) break;

                Optional<MenuItem> itemOpt = menuService.getMenu(resId)
                        .stream()
                        .filter(m -> m.getItemId() == itemId)
                        .findFirst();

                itemOpt.ifPresentOrElse(item -> {
                    int qty = input.readInt("  Quantity: ");
                    if (qty <= 0) {
                        System.out.println("❌ Invalid quantity!");
                        return;
                    }
                    builder.addItem(item, qty);
                    System.out.printf("  ✔ Added: %-20s x%d = ₹%.2f%n",
                            item.getName(), qty, item.getPrice() * qty);
                }, () -> System.out.println("❌ Item not found."));

                itemAdded = true;
            }

            if (!itemAdded) {
                System.out.println("⚠ No items added. Order cancelled.");
                return;
            }

            Order order = builder.build();
            System.out.printf("%n  🧾 Subtotal  : ₹%.2f%n", order.getSubtotal());

            double discount = discountContext.applyDiscount(order.getSubtotal());
            order.setDiscountAmount(discount);
            order.setFinalTotal(order.getSubtotal() - discount);

            if (discount > 0) {
                System.out.printf("  🎉 Discount  : -₹%.2f  (%s)%n",
                        discount, discountContext.getDiscountInfo());
            } else {
                System.out.println("  ℹ  No discount applicable.");
            }
            System.out.printf("  💰 Final Total: ₹%.2f%n", order.getFinalTotal());

            boolean answer = paymentPanel.selectPaymentMethod(order);
            if(!answer){
                throw new Exception("Order not placed");
            }
            invoker.executeCommand(new PlaceOrderCommand(orderService, order));
            deliveryService.assignAgent(order);

            Invoice invoice = invoiceService.generateInvoice(order);
            new InvoicePrinter(invoice).print();

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}