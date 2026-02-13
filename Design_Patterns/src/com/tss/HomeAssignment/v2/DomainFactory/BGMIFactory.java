package com.tss.HomeAssignment.v2.DomainFactory;

import com.tss.HomeAssignment.v2.model.ApplicationFactory;
import com.tss.HomeAssignment.v2.model.PaymentFactory;
import com.tss.HomeAssignment.v2.model.PaymentType;

public class BGMIFactory implements ApplicationFactory {
    @Override
    public void processPayment(PaymentFactory paymentFactory) {
        System.out.println("initiating bgmi payment");
        PaymentType payment = paymentFactory.createPayment();
        payment.pay();
    }
}
