package com.tss.HomeAssignment.v1.test;


import com.tss.HomeAssignment.v1.PaymentService;
import com.tss.HomeAssignment.v1.factory.ECommerce;
import com.tss.HomeAssignment.v1.factory.Gaming;

public class PaymentApplication {

    public static void main(String[] args) {

        PaymentService service = new PaymentService();

        service.processUPI(new ECommerce());
        service.processCreditCard(new Gaming());
    }
}
