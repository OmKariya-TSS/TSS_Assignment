package com.tss.FoodAppV4.factory;


import com.tss.FoodAppV4.payment.*;

import java.util.HashMap;
import java.util.Map;

public class PaymentRegistry {

    private static final Map<String, PaymentProvider> providers = new HashMap<>();

    static {
        providers.put("UPI", new UpiPaymentProvider());
        providers.put("CARD", new CardPaymentProvider());
        providers.put("CASH", new CashPaymentProvider());
    }

    public static PaymentProcessor getProcessor(String method, String param) {

        PaymentProvider provider = providers.get(method.toUpperCase());

        if (provider == null) {
            throw new IllegalArgumentException("Unsupported payment type: " + method);
        }

        return provider.createPayment(param);
    }
}