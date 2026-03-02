package com.tss.FoodAppV3.payment;

import com.tss.FoodAppV3.enums.PaymentMethod;
import com.tss.FoodAppV3.exceptions.ValidationException;

public class CashPayment implements PaymentStrategy {

    private double amountTendered;

    public CashPayment(double amountTendered) {

        if (amountTendered <= 0) {
            throw new ValidationException("Tendered cash amount must be greater than zero.");
        }

        this.amountTendered = amountTendered;
    }

    @Override
    public boolean pay(double amount) {

        if (amount <= 0) {
            throw new ValidationException("Payment amount must be greater than zero.");
        }

        if (amountTendered < amount) {
            System.out.println("Insufficient cash. Payment failed.");
            return false;
        }

        System.out.println("Cash ₹" + amountTendered + " received.");

        double change = getChange(amount);

        if (change > 0) {
            System.out.println("Change returned: ₹" + change);
        }

        System.out.println("Payment successful using Cash.");

        return true;
    }

    @Override
    public String getPaymentMode() {
        return PaymentMethod.CASH.name();
    }

    public double getChange(double amount) {

        if (amount <= 0) {
            throw new ValidationException("Amount must be greater than zero to calculate change.");
        }

        if (amountTendered < amount) {
            return 0; // No negative change allowed
        }

        return amountTendered - amount;
    }
}