package com.tss.HomeAssignment.v2.paymentFactory;

import com.tss.HomeAssignment.v2.model.PaymentFactory;
import com.tss.HomeAssignment.v2.model.PaymentType;
import com.tss.HomeAssignment.v2.payments.UPIPayment;


public class UPIPaymentFactory implements PaymentFactory {

    @Override
    public PaymentType createPayment() {
        return new UPIPayment();
    }
}
