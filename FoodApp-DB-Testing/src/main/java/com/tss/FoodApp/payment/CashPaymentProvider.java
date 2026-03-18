package com.tss.FoodApp.payment;


public class CashPaymentProvider implements PaymentProvider {

    @Override
    public PaymentProcessor createPayment(String param) {

        double amountTendered = Double.parseDouble(param);
        return new CashPayment(amountTendered);
    }
}