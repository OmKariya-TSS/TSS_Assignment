package com.tss.FoodAppV4.payment;

public interface PaymentProcessor {

    boolean pay(double amount);

    String getPaymentMode();
}