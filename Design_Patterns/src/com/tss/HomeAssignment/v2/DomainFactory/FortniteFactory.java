package com.tss.HomeAssignment.v2.DomainFactory;

import com.tss.HomeAssignment.v2.model.ApplicationFactory;
import com.tss.HomeAssignment.v2.model.PaymentFactory;
import com.tss.HomeAssignment.v2.model.PaymentType;

public class FortniteFactory implements ApplicationFactory {
    @Override
    public void processPayment(PaymentFactory paymentFactory) {
        System.out.println("Fortnite game payment initiating request....");
        PaymentType paymentType = paymentFactory.createPayment();
        paymentType.pay();
    }
}
