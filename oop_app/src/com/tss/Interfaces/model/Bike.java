package com.tss.Interfaces.model;

public class Bike extends AbstractVehicle {

    public Bike(int vehicleId, String vehicleName, int fuelLevel) {
        super(vehicleId, vehicleName, fuelLevel);
    }

    @Override
    public void start() {
        if (running) {
            System.out.println("Petrol Bike " + vehicleName + " is already running.");
            return;
        }
        running = true;
        System.out.println("Petrol Bike " + vehicleName + " is starting...");
    }

    @Override
    public void stop() {
        if (!running) {
            System.out.println("Petrol Bike " + vehicleName + " is already stopped.");
            return;
        }
        running = false;
        System.out.println("Petrol Bike " + vehicleName + " is stopping...");
    }
}
