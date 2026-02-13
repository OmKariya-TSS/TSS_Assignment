package com.tss.SRP.test;


import com.tss.SRP.model.Invoice;

public class TaxCalculator {

    public double calculateTax(Invoice invoice) {
        return invoice.getAmount() * invoice.getTax_percentage() / 100;
    }
}
