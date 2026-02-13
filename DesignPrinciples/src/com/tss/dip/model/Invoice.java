package com.tss.dip.model;


public class Invoice {
    int id;
    String description;
    double amount;
    double tax_percentage = 0.18;

    public Invoice(int id, String description, double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.tax_percentage = 0.18;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTax_percentage() {
        return tax_percentage;
    }

    public void setTax_percentage(double tax_percentage) {
        this.tax_percentage = tax_percentage;
    }
}
