package com.tss.HomeAssignment.model;

public class Triangle extends Shapes {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void area() {
        double areaResult = (base * height) / 2;
        System.out.println("The area of the triangle is: " + areaResult);
    }
}
