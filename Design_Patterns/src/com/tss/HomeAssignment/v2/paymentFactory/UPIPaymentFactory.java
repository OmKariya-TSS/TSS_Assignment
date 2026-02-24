package com.tss.HomeAssignment.v2.paymentFactory;

import com.tss.HomeAssignment.v2.interfaces.PaymentFactory;
import com.tss.HomeAssignment.v2.interfaces.PaymentType;
import com.tss.HomeAssignment.v2.payments.UPIPayment;


public class UPIPaymentFactory implements PaymentFactory {

    @Override
    public PaymentType createPayment() {
        return new UPIPayment();
    }
}
