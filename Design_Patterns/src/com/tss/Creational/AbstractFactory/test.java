package com.tss.Creational.AbstractFactory;

import com.tss.Creational.AbstractFactory.model.CarFactory;
import com.tss.Creational.AbstractFactory.model.MarutiFactory;
import com.tss.Creational.AbstractFactory.model.TataFactory;
import com.tss.Creational.AbstractFactory.model.ToyotaFactory;
import com.tss.Creational.AbstractFactory.model.Car;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to car creation");
        System.out.println("1: TOYOTA");
        System.out.println("2:Maruti");
        System.out.println("3: tata");
        int n = scanner.nextInt();
        CarFactory factory;
        Car car;
        switch (n){
            case 1:
                factory = new ToyotaFactory();
                car = factory.makeCar();
                car.drive();
                break;
            case 2:
                factory=new MarutiFactory();
                car = factory.makeCar();
                car.drive();
                break;
            case 3:
                factory = new TataFactory();
                car =factory.makeCar();
                car.drive();
                break;
            default:
                System.out.println("sorry cant create new car for now");
        }
    }
}
