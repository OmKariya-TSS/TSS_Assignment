package com.tss.Interfaces.model;

public class ElectricCar extends AbstractVehicle implements ElectricInterface {

    private int batteryLevel;

    public ElectricCar(int vehicleId, String vehicleName, int batteryLevel) {
        super(vehicleId, vehicleName, 0);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void start() {
        if (running) {
            System.out.println("Electric Car " + vehicleName + " is already running.");
            return;
        }
        if (batteryLevel <= 0) {
            System.out.println("Electric Car " + vehicleName + " has no battery. Please charge it.");
            return;
        }
        running = true;
        System.out.println("Electric Car " + vehicleName + " is starting silently...");
    }

    @Override
    public void stop() {
        if (!running) {
            System.out.println("Electric Car " + vehicleName + " is already stopped.");
            return;
        }
        running = false;
        System.out.println("Electric Car " + vehicleName + " is stopping...");
    }

    @Override
    public void chargeBattery() {
        batteryLevel = 100;
        System.out.println("Electric Car " + vehicleName + " is fully charged ");
    }

    @Override
    public String getFuelStatus() {
        return "Battery Level: " + batteryLevel + "%";
    }

    @Override
    public void playMusic() {
        if (!running) {
            System.out.println("Electric Car " + vehicleName + " is not running. Start the car to play music.");
            return;
        }
        System.out.println("Electric Car " + vehicleName + " is playing music ");
    }
}
