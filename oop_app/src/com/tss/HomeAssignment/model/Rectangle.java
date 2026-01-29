package com.tss.HomeAssignment.model;

public class Rectangle extends Shapes {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void area() {
        double area = this.length * this.width;
        System.out.println("Area of rectangle is: " + area);
    }
}
