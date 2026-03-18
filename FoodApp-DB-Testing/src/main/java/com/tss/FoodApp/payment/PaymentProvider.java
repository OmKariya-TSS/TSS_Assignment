package com.tss.FoodApp.payment;


public interface PaymentProvider {

    PaymentProcessor createPayment(String param);
}