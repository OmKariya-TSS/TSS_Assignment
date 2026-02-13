package com.tss.SRP.test;

import com.tss.SRP.model.Invoice;

public class InvoiceTest {

    public static void main(String[] args) {

        Invoice invoice = new Invoice(1, "Laptop", 20000);

        TaxCalculator taxCalculator = new TaxCalculator();

        InvoicePrinter printer = new InvoicePrinter(taxCalculator);

        printer.print(invoice);
    }
}
