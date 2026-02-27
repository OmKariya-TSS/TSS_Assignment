package com.tss.FoodApp.payment;

public interface PaymentStrategy {

    boolean pay(double amount);

    String getPaymentMode();
}