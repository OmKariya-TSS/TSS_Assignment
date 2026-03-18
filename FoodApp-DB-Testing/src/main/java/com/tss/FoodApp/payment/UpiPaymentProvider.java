package com.tss.FoodApp.payment;


public class UpiPaymentProvider implements PaymentProvider {

    @Override
    public PaymentProcessor createPayment(String param) {
        return new UpiPayment(param);
    }
}