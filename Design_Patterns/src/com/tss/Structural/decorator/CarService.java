package com.tss.Structural.decorator;

import com.tss.Structural.decorator.model.CarInspection;
import com.tss.Structural.decorator.model.ICarService;
import com.tss.Structural.decorator.model.OilChangeDecorator;
import com.tss.Structural.decorator.model.WheelAlign;

public class CarService {
    public static void main(String[] args) {
        ICarService car = new CarInspection();
        double cost =car.getCost();
        System.out.println(cost);
        OilChangeDecorator oilChange=new OilChangeDecorator(car);
        double oilChangeCost = oilChange.getCost();
        System.out.println(oilChangeCost);
        WheelAlign wheelAlign = new WheelAlign(car);
        System.out.println(wheelAlign.getCost());
    }
}
