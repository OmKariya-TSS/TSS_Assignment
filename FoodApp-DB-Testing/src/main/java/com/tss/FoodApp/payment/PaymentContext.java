package com.tss.FoodApp.payment;

public class PaymentContext {
    public PaymentContext() {
    }

    private PaymentProcessor strategy;

    public PaymentContext(PaymentProcessor strategy) {
        this.strategy = strategy;
    }


    public void setStrategy(PaymentProcessor strategy) {
        this.strategy = strategy;
    }

    public boolean executePayment(double amount) {
        return strategy.pay(amount);
    }

    public String getMode() {
        return strategy.getPaymentMode();
    }
}