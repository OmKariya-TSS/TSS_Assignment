package com.tss.FoodApp.payment;

public interface PaymentProcessor {

    boolean pay(double amount);

    String getPaymentMode();
}