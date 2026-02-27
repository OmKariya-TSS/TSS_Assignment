package com.tss.FoodApp.payment;

import com.tss.FoodApp.enums.PaymentMethod;
import com.tss.FoodApp.exceptions.ValidationException;

public class UpiPayment implements PaymentStrategy {

    private String upiId;

    public UpiPayment(String upiId) {

        if (upiId == null || !upiId.matches("^[a-zA-Z0-9._-]+@[a-zA-Z]+$")) {
            throw new ValidationException("Invalid UPI ID format.");
        }

        this.upiId = upiId;
    }

    @Override
    public boolean pay(double amount) {

        if (amount <= 0) {
            throw new ValidationException("Payment amount must be greater than zero.");
        }

        System.out.println("Processing UPI payment...");
        System.out.println("UPI ₹" + amount + " sent successfully from " + upiId);

        return true;
    }

    @Override
    public String getPaymentMode() {
        return PaymentMethod.UPI.name();
    }

    public boolean validateUpiId() {
        return upiId != null && upiId.matches("^[a-zA-Z0-9._-]+@[a-zA-Z]+$");
    }
}