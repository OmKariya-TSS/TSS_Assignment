package com.tss.Interfaces.service;

import com.tss.Interfaces.model.*;

public class VehicleService {

    private Vehicle[] vehicles;
    private int count;

    public VehicleService(int capacity) {
        vehicles = new Vehicle[capacity];
        count = 0;
    }

    public void addVehicle(Vehicle vehicle) {
        if (count >= vehicles.length) {
            System.out.println("Vehicle storage is full!");
            return;
        }
        vehicles[count++] = vehicle;
        System.out.println("Vehicle added successfully.");
    }

    public void startVehicle(int index) {
        Vehicle vehicle = getVehicle(index);
        if (vehicle != null) {
            vehicle.start();
        }
    }

    public void stopVehicle(int index) {
        Vehicle vehicle = getVehicle(index);
        if (vehicle != null) {
            vehicle.stop();
        }
    }

    public void showFuelStatus(int index) {
        Vehicle vehicle = getVehicle(index);
        if (vehicle != null) {
            System.out.println(vehicle.getFuelStatus());
        }
    }

    public void chargeVehicle(int index) {
        Vehicle vehicle = getVehicle(index);

        if (vehicle instanceof ElectricCar) {
            ElectricCar ev = (ElectricCar) vehicle;
            ev.chargeBattery();
        } else {
            System.out.println("Selected vehicle is not electric.");
        }
    }

    public void horn(int index) {
        Vehicle vehicle = getVehicle(index);
        if (vehicle != null) {
            vehicle.horn();
        }
    }
    public void playMusic(int index) {
        Vehicle vehicle = getVehicle(index);
        if (vehicle != null) {
            vehicle.playMusic();
        }
    }
    public void listVehicles() {
        for (int i = 0; i < count; i++) {
            Vehicle v = vehicles[i];
            if (v instanceof Car) System.out.println((i + 1) + " : " + ((Car) v).getVehicleName());
            else if (v instanceof ElectricCar) System.out.println((i + 1)  + " : " + ((ElectricCar) v).getVehicleName());
            else if (v instanceof Bike)  System.out.println((i + 1) + " : " + ((Bike) v).getVehicleName());
            else if (v instanceof ElectricBike)  System.out.println((i + 1)  + " : " + ((ElectricBike) v).getVehicleName());
            else if (v instanceof Trucks)  System.out.println((i + 1) + " : " + ((Trucks) v).getVehicleName());
            else if (v instanceof ElectricTruck)  System.out.println((i + 1) + " : " + ((ElectricTruck) v).getVehicleName());
        }
    }


    public Vehicle getVehicle(int index) {
        if (index < 0 || index >= count) {
            System.out.println("Invalid vehicle index.");
            return null;
        }
        return vehicles[index];
    }
    public int getCount() {
        return count;
    }
}
