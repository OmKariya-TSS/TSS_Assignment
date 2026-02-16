package com.tss.Structural.HomeAssignment.decoratorHomeAssignment.model;

public class PremiumHat implements Hat{
    String description;
    String name;
    String color;

    public PremiumHat(String description, String name, String color) {
        this.description = description;
        this.name = name;
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }
}
