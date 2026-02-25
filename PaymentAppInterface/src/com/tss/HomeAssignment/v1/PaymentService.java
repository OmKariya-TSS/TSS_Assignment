package com.tss.HomeAssignment.v1;


import com.tss.HomeAssignment.v1.factory.ApplicationFactory;
import com.tss.HomeAssignment.v1.factory.PaymentMethod;

public class PaymentService {

    public void processCreditCard(ApplicationFactory factory) {
        PaymentMethod payment = factory.createCreditCardPayment();
        payment.pay();
    }

    public void processUPI(ApplicationFactory factory) {
        PaymentMethod payment = factory.createUPIPayment();
        payment.pay();
    }

    public void processWallet(ApplicationFactory factory) {
        PaymentMethod payment = factory.createWalletPayment();
        payment.pay();
    }
}
