package com.tss.FoodApp.payment;

import com.tss.FoodApp.enums.PaymentMethod;
import com.tss.FoodApp.exceptions.ValidationException;

public class CardPayment implements PaymentStrategy {

    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public CardPayment(String cardNumber,
                       String cardHolderName,
                       String expiryDate,
                       String cvv) {

        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            throw new ValidationException("Card number must be exactly 16 digits.");
        }

        if (cardHolderName == null || cardHolderName.isBlank()) {
            throw new ValidationException("Card holder name cannot be empty.");
        }

        if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            throw new ValidationException("Expiry date must be in MM/YY format.");
        }

        if (cvv == null || !cvv.matches("\\d{3}")) {
            throw new ValidationException("CVV must be exactly 3 digits.");
        }

        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(double amount) {

        if (amount <= 0) {
            throw new ValidationException("Payment amount must be greater than zero.");
        }

        System.out.println("Processing card payment...");
        System.out.println("₹" + amount + " paid successfully using Card.");
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("**** **** **** " + cardNumber.substring(cardNumber.length() - 4));

        return true;
    }

    @Override
    public String getPaymentMode() {
        return PaymentMethod.CARD.name();
    }
}