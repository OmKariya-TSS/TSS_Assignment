package com.tss.Structural.Adapter.model;

public class Chocolate implements Item{
    private String name;
    private double price;

    public Chocolate(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Chocolate{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public double getItemPrice() {
        return price;
    }
}
