package com.tss.FoodApp.factory;

import com.tss.FoodApp.enums.PaymentMethod;
import com.tss.FoodApp.payment.CardPayment;
import com.tss.FoodApp.payment.CashPayment;
import com.tss.FoodApp.payment.PaymentStrategy;
import com.tss.FoodApp.payment.UpiPayment;

public class PaymentFactory {
    public static PaymentStrategy getStrategy(String method, String param) {
        if (method.equalsIgnoreCase(PaymentMethod.CASH.name())) {
            double amountTendered = Double.parseDouble(param);
            return new CashPayment(amountTendered);
        } else if (method.equalsIgnoreCase(PaymentMethod.UPI.name())) {
            return new UpiPayment(param);
        } else if (method.equalsIgnoreCase(PaymentMethod.CARD.name())) {
            String[] parts = param.split(",");
            if (parts.length != 4) {
                throw new IllegalArgumentException("Invalid card parameters");
            }
            return new CardPayment(
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim(),
                    parts[3].trim()
            );
        } else {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
    }
}