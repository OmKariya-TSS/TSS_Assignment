package com.tss.dip.model;

public class TaxCalculator {

    private Logger logger;

    public TaxCalculator(Logger logger) {
        this.logger = logger;
    }

    public double calculateTax(Invoice invoice) {

        double tax =
                invoice.getAmount() *
                        invoice.getTax_percentage() / 100;

        logger.log("Tax calculated for Invoice ID "
                + invoice.getId()
                + " = " + tax);

        return tax;
    }
}
