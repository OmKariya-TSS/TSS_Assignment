package com.tss.HomeAssignment.v2.model;


public interface ApplicationFactory {
    void processPayment(PaymentFactory paymentFactory);
    //other domain related methods if implemented
}
