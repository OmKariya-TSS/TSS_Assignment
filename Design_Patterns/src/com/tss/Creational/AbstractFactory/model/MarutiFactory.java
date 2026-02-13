package com.tss.Creational.AbstractFactory.model;

public class MarutiFactory implements CarFactory{

    @Override
    public Car makeCar() {
        return new Maruti();
    }
}
