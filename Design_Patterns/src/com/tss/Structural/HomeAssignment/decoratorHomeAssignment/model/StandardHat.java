package com.tss.Structural.HomeAssignment.decoratorHomeAssignment.model;

public class StandardHat implements Hat{
    String color;
    String description;
    String name;

    public StandardHat(String color, String name, String description) {
        this.color = color;
        this.name = name;
        this.description = description;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
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
