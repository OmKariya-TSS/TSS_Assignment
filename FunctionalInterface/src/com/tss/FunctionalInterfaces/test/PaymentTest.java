package com.tss.FunctionalInterfaces.test;

import com.tss.FunctionalInterfaces.NegativeAmountException;
import com.tss.FunctionalInterfaces.model.Payment;

import java.util.Scanner;

public class PaymentTest {
    static Payment creditCardPayment = amount -> amount < 100000;
    static Payment UpiPayment = amount -> amount<50000;
    static Payment NetbankingPayment = amount -> true;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to functional interface demo");
        menu();
    }
    public static void menu(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("1. Credit card");
        System.out.println("2. Upi payment");
        System.out.println("3: netbanking payment");
        int n= scanner.nextInt();
        switch (n) {
            case 1:
                boolean ans = creditCardPayment.pay(takeAmount());
                System.out.println(ans);
                menu();
                break;
            case 2:
                ans =UpiPayment.pay(takeAmount());
                System.out.println(ans);
                menu();
                break;
            case 3:
                ans = NetbankingPayment.pay(takeAmount());
                System.out.println(ans);
                menu();
                break;
            default:
                System.out.println("please enter valid choice");
                menu();
        }
    }
    static Scanner scanner = new Scanner(System.in);

    public static double takeAmount() {
        while (true) {
            try {
                System.out.println("Enter amount:");
                double amount = scanner.nextDouble();

                if (amount < 0) {
                    throw new NegativeAmountException("Amount must be positive");
                }
                return amount;
            } catch (NegativeAmountException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
