package com.tss.HomeAssignment.model;

public class Circle extends Shapes {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void area() {
        double areaResult = Math.PI * Math.pow(radius, 2);
        System.out.println("The area of the circle is: " + areaResult);
    }
}
