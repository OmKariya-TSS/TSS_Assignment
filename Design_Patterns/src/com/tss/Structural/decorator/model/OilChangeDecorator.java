package com.tss.Structural.decorator.model;

public class OilChangeDecorator extends CarServiceDecorator{
    public OilChangeDecorator(ICarService carObj) {
        super(carObj);
    }
    @Override
    public double getCost() {
        return super.getCost() + 200;
    }
}
