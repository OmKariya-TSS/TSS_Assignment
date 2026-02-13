package com.tss.Creational.AbstractFactory.model;

public class ToyotaFactory implements CarFactory{
    @Override
    public Car makeCar() {
        return new Toyota();
    }
}
