package com.tss.FoodApp.ui;

import com.tss.FoodApp.enums.PaymentMethod;
import com.tss.FoodApp.factory.PaymentFactory;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.payment.PaymentContext;
import com.tss.FoodApp.payment.PaymentStrategy;

import java.util.InputMismatchException;

public class PaymentPanel {

    private final PaymentContext paymentContext;
    private final InputHelper input;

    public PaymentPanel(PaymentContext paymentContext, InputHelper input) {
        this.paymentContext = paymentContext;
        this.input          = input;
    }

    public void selectPaymentMethod(Order order) {
        try {
            System.out.println("\n  💳 Payment Method:");
            System.out.println("    1. CASH");
            System.out.println("    2. UPI");
            System.out.println("    3. CARD");

            int choice = input.readInt("  Choose: ");

            PaymentMethod method;
            String param;

            switch (choice) {
                case 1 -> {
                    method = PaymentMethod.CASH;
                    System.out.print("  Amount tendered ₹: ");
                    param = input.getScanner().nextLine();
                }
                case 2 -> {
                    method = PaymentMethod.UPI;
                    System.out.print("  Enter UPI ID: ");
                    param = input.getScanner().nextLine();
                }
                case 3 -> {
                    method = PaymentMethod.CARD;
                    System.out.print("  Card Number     : ");
                    String number = input.getScanner().nextLine();
                    System.out.print("  Card Holder Name: ");
                    String name = input.getScanner().nextLine();
                    System.out.print("  Expiry (MM/YY)  : ");
                    String expiry = input.getScanner().nextLine();
                    System.out.print("  CVV             : ");
                    String cvv = input.getScanner().nextLine();
                    param = number + "," + name + "," + expiry + "," + cvv;
                }
                default -> {
                    System.out.println("❌ Invalid, defaulting to CASH.");
                    method = PaymentMethod.CASH;
                    param = String.valueOf(order.getFinalTotal());
                }
            }

            order.setPaymentMethod(method);
            PaymentStrategy strategy = PaymentFactory.getStrategy(method.name(), param);
            paymentContext.setStrategy(strategy);
            paymentContext.executePayment(order.getFinalTotal());

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}