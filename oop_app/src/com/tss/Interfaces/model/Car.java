package com.tss.Interfaces.model;

public class Car extends AbstractVehicle {

    public Car(int vehicleId, String vehicleName, int fuelLevel) {
        super(vehicleId, vehicleName, fuelLevel);
    }

    @Override
    public void start() {
        if (running) {
            System.out.println("Petrol Car " + vehicleName + " is already running.");
            return;
        }
        running = true;
        System.out.println("Petrol Car " + vehicleName + " is starting...");
    }

    @Override
    public void stop() {
        if (!running) {
            System.out.println("Petrol Car " + vehicleName + " is already stopped.");
            return;
        }
        running = false;
        System.out.println("Petrol Car " + vehicleName + " is stopping...");
    }

    @Override
    public void playMusic() {
        if (!running) {
            System.out.println("Petrol Car " + vehicleName + " is not running. Start the car to play music.");
            return;
        }
        System.out.println("Petrol Car " + vehicleName + " is playing music through speakers ");
    }
}
