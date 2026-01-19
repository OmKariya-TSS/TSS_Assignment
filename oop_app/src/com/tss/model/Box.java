package com.tss.model;

public class Box {
    double width;
    double height;
    double depth;
    public Box(){
        this.width =0;
        this.depth=0;
        this.height=0;
    }
    public Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
    public double getVolume(double height,double width,double depth){
        return height * width * depth;
    }
}
