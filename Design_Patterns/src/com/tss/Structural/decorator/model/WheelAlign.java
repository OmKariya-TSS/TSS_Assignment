package com.tss.Structural.decorator.model;

public class WheelAlign extends CarServiceDecorator{
    public WheelAlign(ICarService carObj) {
        super(carObj);
    }

    @Override
    public double getCost() {
        return super.getCost()+300;
    }
}
