package com.tss.Interfaces.model;

public class Trucks extends AbstractVehicle {

    public Trucks(int vehicleId, String vehicleName, int fuelLevel) {
        super(vehicleId, vehicleName, fuelLevel);
    }

    @Override
    public void start() {
        if (running) {
            System.out.println("Petrol Truck " + vehicleName + " is already running.");
            return;
        }
        if (fuelLevel <= 0) {
            System.out.println("Petrol Truck " + vehicleName + " has no fuel. Please refuel.");
            return;
        }
        running = true;
        System.out.println("Petrol Truck " + vehicleName + " is starting with heavy engine...");
    }

    @Override
    public void stop() {
        if (!running) {
            System.out.println("Petrol Truck " + vehicleName + " is already stopped.");
            return;
        }
        running = false;
        System.out.println("Petrol Truck " + vehicleName + " is stopping...");
    }

    @Override
    public void horn() {
        System.out.println("Petrol Truck horn: HOOOONK! ");
    }

    @Override
    public void playMusic() {
        if (!running) {
            System.out.println("Petrol Truck " + vehicleName + " is not running. Start it to play music.");
            return;
        }
        System.out.println("Petrol Truck " + vehicleName + " is playing music ");
    }
}
