package com.tss.Creational.factory.model;

import static com.tss.Creational.factory.model.CarType.*;

public class CarFactory {
    public static Car createCar(CarType type){
        Car car=null;
        if(type==TOYOTA){
             car = new Toyota();
        }
        else if(type==MARUTI){
            car = new Maruti();
        }
        else if(type==HYUNDAI){
            car = new Hyundai();
        }
        else{
            System.out.println("cant create other cars");
        }
        return car;
    }
}
