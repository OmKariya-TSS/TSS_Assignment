package com.tss.HomeAssignment.v1.factory;

public class CreditCardPayment implements PaymentMethod {

    private final String applicationName;

    public CreditCardPayment(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void pay() {
        System.out.println(applicationName +
                " processing payment via Credit Card...");
    }
}
