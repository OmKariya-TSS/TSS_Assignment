package com.tss.FoodAppV4.payment;


public class UpiPaymentProvider implements PaymentProvider {

    @Override
    public PaymentProcessor createPayment(String param) {
        return new UpiPayment(param);
    }
}