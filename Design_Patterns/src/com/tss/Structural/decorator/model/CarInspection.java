package com.tss.Structural.decorator.model;

public class CarInspection implements ICarService {
    @Override
    public double getCost() {
        return 5000;
    }
}
