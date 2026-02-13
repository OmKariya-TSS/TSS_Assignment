package com.tss.HomeAssignment.v2.test;

import com.tss.HomeAssignment.v2.DomainFactory.AmazonFactory;
import com.tss.HomeAssignment.v2.DomainFactory.FlipkartFactory;
import com.tss.HomeAssignment.v2.DomainFactory.FortniteFactory;
import com.tss.HomeAssignment.v2.model.ApplicationFactory;
import com.tss.HomeAssignment.v2.model.PaymentFactory;
import com.tss.HomeAssignment.v2.paymentFactory.CreditCardPaymentFactory;
import com.tss.HomeAssignment.v2.paymentFactory.DebitCardFactory;
import com.tss.HomeAssignment.v2.paymentFactory.UPIPaymentFactory;

public class PaymentApp {
    public static void main(String[] args) {
        ApplicationFactory flipkart = new FlipkartFactory();
        ApplicationFactory amazon = new AmazonFactory();
        ApplicationFactory fortnite = new FortniteFactory();
        PaymentFactory upiFactory = new UPIPaymentFactory();
        PaymentFactory creditFactory = new CreditCardPaymentFactory();
        PaymentFactory debitCard = new DebitCardFactory();
        flipkart.processPayment(upiFactory);
        amazon.processPayment(creditFactory);
        fortnite.processPayment(debitCard);


    }
}
