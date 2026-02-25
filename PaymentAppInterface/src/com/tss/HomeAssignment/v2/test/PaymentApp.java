package com.tss.HomeAssignment.v2.test;

import com.tss.HomeAssignment.v2.DomainFactory.ECommerce.AmazonFactory;
import com.tss.HomeAssignment.v2.DomainFactory.ECommerce.FlipkartFactory;
import com.tss.HomeAssignment.v2.DomainFactory.Gaming.BGMIFactory;
import com.tss.HomeAssignment.v2.DomainFactory.Gaming.FortniteFactory;
import com.tss.HomeAssignment.v2.interfaces.ApplicationFactory;
import com.tss.HomeAssignment.v2.interfaces.PaymentFactory;
import com.tss.HomeAssignment.v2.paymentFactory.CreditCardPaymentFactory;
import com.tss.HomeAssignment.v2.paymentFactory.DebitCardFactory;
import com.tss.HomeAssignment.v2.paymentFactory.UPIPaymentFactory;

import java.util.Scanner;

public class PaymentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n========== PAYMENT APPLICATION ==========");
            System.out.println("Select Category:");
            System.out.println("1. E-Commerce");
            System.out.println("2. Gaming");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int categoryChoice = scanner.nextInt();

            if (categoryChoice == 0) {
                System.out.println("Exiting application. Goodbye!");
                break;
            }

            ApplicationFactory applicationFactory;

            switch (categoryChoice) {
                case 1:
                    System.out.println("\n--- E-Commerce Platforms ---");
                    System.out.println("1. Amazon");
                    System.out.println("2. Flipkart");
                    System.out.println("0. Back");
                    System.out.print("Enter choice: ");
                    int ecomChoice = scanner.nextInt();
                    switch (ecomChoice) {
                        case 1:
                            applicationFactory = new AmazonFactory();
                            break;
                        case 2:
                            applicationFactory = new FlipkartFactory();
                            break;
                        case 0:
                            continue;
                        default:
                            System.out.println("Invalid choice! Try again.");
                            continue;
                    }
                    break;

                case 2:
                    System.out.println("\n--- Gaming Platforms ---");
                    System.out.println("1. BGMI");
                    System.out.println("2. Fortnite");
                    System.out.println("0. Back");
                    System.out.print("Enter choice: ");
                    int gameChoice = scanner.nextInt();
                    switch (gameChoice) {
                        case 1:
                            applicationFactory = new BGMIFactory();
                            break;
                        case 2:
                            applicationFactory = new FortniteFactory();
                            break;
                        case 0:
                            continue;
                        default:
                            System.out.println("Invalid choice! Try again.");
                            continue;
                    }
                    break;

                default:
                    System.out.println("Invalid category! Try again.");
                    continue;
            }

            System.out.println("\nSelect Payment Method:");
            System.out.println("1. UPI");
            System.out.println("2. Credit Card");
            System.out.println("3. Debit Card");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            int paymentChoice = scanner.nextInt();

            PaymentFactory paymentFactory;
            switch (paymentChoice) {
                case 1:
                    paymentFactory = new UPIPaymentFactory();
                    break;
                case 2:
                    paymentFactory = new CreditCardPaymentFactory();
                    break;
                case 3:
                    paymentFactory = new DebitCardFactory();
                    break;
                case 0:
                    continue;
                default:
                    System.out.println("Invalid payment choice! Try again.");
                    continue;
            }

            System.out.println("\n-----------------------------------------");
            applicationFactory.processPayment(paymentFactory);
            System.out.println("-----------------------------------------");
        }
    }
}