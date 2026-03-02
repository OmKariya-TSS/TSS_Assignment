package com.tss.FoodAppV3.payment;

public class PaymentContext {
    public PaymentContext() {
    }

    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }


    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean executePayment(double amount) {
        return strategy.pay(amount);
    }

    public String getMode() {
        return strategy.getPaymentMode();
    }
}