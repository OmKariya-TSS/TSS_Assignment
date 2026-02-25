package com.tss.HomeAssignment.v1.factory;


public class Gaming implements ApplicationFactory {

    @Override
    public PaymentMethod createCreditCardPayment() {
        return new CreditCardPayment("gaming");
    }

    @Override
    public PaymentMethod createUPIPayment() {
        return new UPIPayment("gaming");
    }

    @Override
    public PaymentMethod createWalletPayment() {
        return new WalletPayment("gaming");
    }
}
