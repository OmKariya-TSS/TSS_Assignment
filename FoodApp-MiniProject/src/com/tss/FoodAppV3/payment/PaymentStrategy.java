package com.tss.FoodAppV3.payment;

public interface PaymentStrategy {

    boolean pay(double amount);

    String getPaymentMode();
}