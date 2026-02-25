package com.tss.HomeAssignment.v2.payments;

import com.tss.HomeAssignment.v2.interfaces.PaymentType;

public class UPIPayment implements PaymentType {
    @Override
    public void pay() {
        System.out.println("doing payment via upi");
        System.out.println("UPI Payment successfull....");
    }
}

