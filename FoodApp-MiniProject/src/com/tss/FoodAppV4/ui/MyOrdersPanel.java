package com.tss.FoodAppV4.ui;


import com.tss.FoodAppV4.command.CancelOrderCommand;
import com.tss.FoodAppV4.command.CommandInvoker;
import com.tss.FoodAppV4.model.Customer;
import com.tss.FoodAppV4.model.Invoice;
import com.tss.FoodAppV4.model.Order;
import com.tss.FoodAppV4.service.interfaces.IInvoiceService;
import com.tss.FoodAppV4.service.interfaces.IOrderService;

import java.util.List;

public class MyOrdersPanel {

    private final IOrderService orderService;
    private final IInvoiceService invoiceService;
    private final CommandInvoker invoker;
    private final Customer customer;
    private final InputHelper input;

    public MyOrdersPanel(IOrderService orderService,
                         IInvoiceService invoiceService,
                         CommandInvoker invoker,
                         Customer customer,
                         InputHelper input) {
        this.orderService   = orderService;
        this.invoiceService = invoiceService;
        this.invoker        = invoker;
        this.customer       = customer;
        this.input          = input;
    }

    public void viewMyOrders() {
        try {
            List<Order> orders = orderService.getOrdersByCustomer(customer.getUserId());
            if (orders.isEmpty()) {
                System.out.println("⚠ No orders placed yet.");
                return;
            }
            System.out.println("\n--- 📦 My Orders ---");
            orders.forEach(System.out::println);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void viewInvoice() {
        int orderId = input.readInt("  Enter Order ID: ");
        try {
            Invoice invoice = invoiceService.getInvoiceByOrderId(orderId);
            if (invoice == null) {
                System.out.println("❌ Invoice not found for Order #" + orderId);
                return;
            }
            new InvoicePrinter(invoice).print();
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    public void cancelOrder() {
        try {
            viewMyOrders();
            int orderId = input.readInt("  Enter Order ID to cancel: ");
            invoker.executeCommand(new CancelOrderCommand(orderService, orderId));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void undoLastAction() {
        invoker.undoLast();
    }
}