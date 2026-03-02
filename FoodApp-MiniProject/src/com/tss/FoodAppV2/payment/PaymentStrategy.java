package com.tss.FoodAppV2.payment;

public interface PaymentStrategy {

    boolean pay(double amount);

    String getPaymentMode();
}