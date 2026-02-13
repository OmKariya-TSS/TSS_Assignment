package com.tss.HomeAssignment.v1.factory;


public class ECommerce implements ApplicationFactory {

    @Override
    public PaymentMethod createCreditCardPayment() {
        return new CreditCardPayment("Ecommerce");
    }

    @Override
    public PaymentMethod createUPIPayment() {
        return new UPIPayment("Ecommerce");
    }

    @Override
    public PaymentMethod createWalletPayment() {
        return new WalletPayment("ecommerce");
    }
}
