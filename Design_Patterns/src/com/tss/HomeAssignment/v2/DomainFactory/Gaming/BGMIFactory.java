package com.tss.HomeAssignment.v2.DomainFactory.Gaming;

import com.tss.HomeAssignment.v2.interfaces.ApplicationFactory;
import com.tss.HomeAssignment.v2.interfaces.PaymentFactory;
import com.tss.HomeAssignment.v2.interfaces.PaymentType;

public class BGMIFactory implements ApplicationFactory {
    @Override
    public void processPayment(PaymentFactory paymentFactory) {
        System.out.println("initiating bgmi payment");
        PaymentType payment = paymentFactory.createPayment();
        payment.pay();
    }
}
