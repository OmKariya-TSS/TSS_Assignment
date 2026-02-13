package com.tss.Creational.AbstractFactory.model;

public class TataFactory implements CarFactory{

    @Override
    public Car makeCar() {
        return new Tata();
    }
}
