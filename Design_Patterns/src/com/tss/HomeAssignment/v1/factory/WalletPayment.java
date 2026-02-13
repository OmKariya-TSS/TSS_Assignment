package com.tss.HomeAssignment.v1.factory;


public class WalletPayment implements PaymentMethod {

    private String applicationName;

    public WalletPayment(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void pay() {
        System.out.println(applicationName + " processing payment via Wallet...");
        System.out.println("Payment successful using Wallet ");
    }
}
