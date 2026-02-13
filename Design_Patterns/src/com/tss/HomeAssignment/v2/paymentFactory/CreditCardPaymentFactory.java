package com.tss.HomeAssignment.v2.paymentFactory;


import com.tss.HomeAssignment.v2.model.PaymentFactory;
import com.tss.HomeAssignment.v2.model.PaymentType;
import com.tss.HomeAssignment.v2.payments.CreditCardPayment;

public class CreditCardPaymentFactory implements PaymentFactory {
    public PaymentType createPayment() {
        return new CreditCardPayment();
    }
}
