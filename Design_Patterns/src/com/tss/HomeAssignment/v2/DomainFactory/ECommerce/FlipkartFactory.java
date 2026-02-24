package com.tss.HomeAssignment.v2.DomainFactory.ECommerce;

import com.tss.HomeAssignment.v2.interfaces.ApplicationFactory;
import com.tss.HomeAssignment.v2.interfaces.PaymentFactory;
import com.tss.HomeAssignment.v2.interfaces.PaymentType;

public class FlipkartFactory implements ApplicationFactory {

    @Override
    public void processPayment(PaymentFactory paymentFactory) {
        System.out.println("amazon initiating payment...");
        PaymentType payment = paymentFactory.createPayment();
        payment.pay();
    }
}
