package com.tss.FoodAppV4.payment;


import com.tss.FoodAppV4.payment.PaymentProcessor;

public interface PaymentProvider {

    PaymentProcessor createPayment(String param);
}