package com.tss.Structural.decorator.model;

public abstract class CarServiceDecorator implements ICarService {
    private ICarService carObj;
    public CarServiceDecorator(ICarService carObj){
        this.carObj = carObj;
    }
    public double getCost(){
        return carObj.getCost();
    }

}
