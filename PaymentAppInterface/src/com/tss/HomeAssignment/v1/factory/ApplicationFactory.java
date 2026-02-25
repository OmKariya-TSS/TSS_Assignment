package com.tss.HomeAssignment.v1.factory;


public interface ApplicationFactory {

    PaymentMethod createCreditCardPayment();

    PaymentMethod createUPIPayment();

    PaymentMethod createWalletPayment();
}
