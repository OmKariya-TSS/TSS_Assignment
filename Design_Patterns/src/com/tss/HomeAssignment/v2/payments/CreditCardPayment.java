package com.tss.HomeAssignment.v2.payments;


import com.tss.HomeAssignment.v2.interfaces.PaymentType;

public class CreditCardPayment implements PaymentType {

    @Override
    public void pay() {
        System.out.println("Processing payment via Credit Card...");
        System.out.println("Credit Card payment successful ");
    }
}
