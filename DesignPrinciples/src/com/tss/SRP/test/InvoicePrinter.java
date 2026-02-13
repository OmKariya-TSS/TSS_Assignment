package com.tss.SRP.test;


import com.tss.SRP.model.Invoice;

public class InvoicePrinter {

    private TaxCalculator taxCalculator;
    public InvoicePrinter(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public void print(Invoice invoice) {

        double taxAmount = taxCalculator.calculateTax(invoice);
        double totalAmount = invoice.getAmount() + taxAmount;

        System.out.println("------- INVOICE -------");
        System.out.println("Invoice ID: " + invoice.getId());
        System.out.println("Description: " + invoice.getDescription());
        System.out.println("Amount: " + invoice.getAmount());
        System.out.println("Tax (" + invoice.getTax_percentage() + "%): " + taxAmount);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("-----------------------");
    }
}
