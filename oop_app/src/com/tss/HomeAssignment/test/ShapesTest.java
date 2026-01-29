package com.tss.HomeAssignment.test;

import com.tss.HomeAssignment.model.Circle;
import com.tss.HomeAssignment.model.Rectangle;
import com.tss.HomeAssignment.model.Shapes;
import com.tss.HomeAssignment.model.Triangle;

public class ShapesTest {
    public static void main(String[] args) {
        Shapes circle= new Circle(2);
        Shapes rectangle  = new Rectangle(2,2);
        Shapes triangle  = new Triangle(2,2);
        circle.area();
        rectangle.area();
        triangle.area();
    }
}
