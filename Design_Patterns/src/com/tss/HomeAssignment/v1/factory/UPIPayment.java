package com.tss.HomeAssignment.v1.factory;

public class UPIPayment implements PaymentMethod {

    private String applicationName;

    public UPIPayment(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void pay() {
        System.out.println(applicationName + " processing payment via UPI...");
        System.out.println("Payment successful using UPI");
    }
}
