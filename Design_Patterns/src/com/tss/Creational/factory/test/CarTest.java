package com.tss.Creational.factory.test;

import com.tss.Creational.factory.model.Car;
import com.tss.Creational.factory.model.CarFactory;
import com.tss.Creational.factory.model.CarType;

import java.util.Scanner;

public class CarTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to car creation");
        System.out.println("1: Maruti");
        System.out.println("2: Hyundai");
        System.out.println("3: Toyota");
        int n = scanner.nextInt();
        Car car;
        switch (n){
            case 1:
                car = CarFactory.createCar(CarType.MARUTI);
                car.drive();
                break;
            case 2:
                car = CarFactory.createCar(CarType.HYUNDAI);
                car.drive();
                break;
            case 3:
                car = CarFactory.createCar(CarType.TOYOTA);
                car.drive();
                break;
            default:
                System.out.println("cant create car");
        }

    }
}
