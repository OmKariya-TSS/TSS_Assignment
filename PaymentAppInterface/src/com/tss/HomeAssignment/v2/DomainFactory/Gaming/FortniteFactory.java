package com.tss.HomeAssignment.v2.DomainFactory.Gaming;

import com.tss.HomeAssignment.v2.interfaces.ApplicationFactory;
import com.tss.HomeAssignment.v2.interfaces.PaymentFactory;
import com.tss.HomeAssignment.v2.interfaces.PaymentType;

public class FortniteFactory implements ApplicationFactory {
    @Override
    public void processPayment(PaymentFactory paymentFactory) {
        System.out.println("Fortnite game payment initiating request....");
        PaymentType paymentType = paymentFactory.createPayment();
        paymentType.pay();
    }
}
