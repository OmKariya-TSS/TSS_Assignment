package com.tss.HomeAssignment.v2.payments;

import com.tss.HomeAssignment.v2.interfaces.PaymentType;

public class DebitCard implements PaymentType {
    @Override
    public void pay() {
        System.out.println("doing payment via debit card");
        System.out.println("debit card payment successfull....");
    }
}
