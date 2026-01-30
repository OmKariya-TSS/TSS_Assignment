package com.tss.Interfaces.model;

public abstract class AbstractVehicle implements Vehicle {

    protected int vehicleId;
    protected String vehicleName;
    protected int fuelLevel;

    protected boolean running = false;

    public AbstractVehicle(int vehicleId, String vehicleName, int fuelLevel) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.fuelLevel = fuelLevel;
    }

    @Override
    public String getFuelStatus() {
        return "Fuel Level: " + fuelLevel;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public boolean isRunning() {
        return running;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public abstract void start();

    public abstract void stop();
}
