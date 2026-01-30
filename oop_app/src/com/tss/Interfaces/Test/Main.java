package com.tss.Interfaces.Test;

import com.tss.Interfaces.model.*;
import com.tss.Interfaces.service.VehicleService;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static void menu(VehicleService service) {
        System.out.println("\n===== VEHICLE MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Vehicle");
        System.out.println("2. Start Vehicle");
        System.out.println("3. Stop Vehicle");
        System.out.println("4. Show Fuel/Battery Status");
        System.out.println("5. Charge Electric Vehicle");
        System.out.println("6. Play Horn");
        System.out.println("7. Play Music");
        System.out.println("8. List All Vehicles");
        System.out.println("9. Perform Vehicle Inspection");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        int choice = readInt();

        switch (choice) {
            case 1 -> addVehicle(service);
            case 2 -> startVehicle(service);
            case 3 -> stopVehicle(service);
            case 4 -> showFuelStatus(service);
            case 5 -> chargeVehicle(service);
            case 6 -> hornVehicle(service);
            case 7 -> playMusic(service);
            case 8 -> service.listVehicles();
            case 9 -> inspectVehicle(service);
            case 0 -> {
                System.out.println("Exiting Vehicle Management System. Goodbye!");
                return;
            }
            default -> System.out.println("Invalid choice! Try again.");
        }
        menu(service);
    }

    private static void addVehicle(VehicleService service) {
        System.out.println("Select Vehicle Category:");
        System.out.println("1. Car");
        System.out.println("2. Bike");
        System.out.println("3. Truck");
        System.out.print("Enter choice: ");
        int category = readInt();

        System.out.println("Select Power Type:");
        System.out.println("1. Petrol");
        System.out.println("2. Electric");
        System.out.print("Enter choice: ");
        int powerType = readInt();

        System.out.print("Enter Vehicle Name: ");
        String name = scanner.nextLine();

        int level;
        if (powerType == 1) {
            System.out.print("Enter fuel in litres: ");
            level = readInt();
            if (level < 0) level = 0;
        } else if (powerType == 2) {
            System.out.print("Enter battery level (0-100%): ");
            level = readInt();
            level = Math.max(0, Math.min(100, level));
        } else {
            System.out.println("Invalid power type!");
            return;
        }

        Vehicle vehicle;

        switch (category) {
            case 1 -> {
                if (powerType == 1)
                    vehicle = new Car(service.getCount() + 1, name, level);
                else
                    vehicle = new ElectricCar(service.getCount() + 1, name, level);
            }
            case 2 -> {
                if (powerType == 1)
                    vehicle = new Bike(service.getCount() + 1, name, level);
                else
                    vehicle = new ElectricBike(service.getCount() + 1, name, level);
            }
            case 3 -> {
                if (powerType == 1)
                    vehicle = new Trucks(service.getCount() + 1, name, level);
                else
                    vehicle = new ElectricTruck(service.getCount() + 1, name, level);
            }
            default -> {
                System.out.println("Invalid vehicle category!");
                return;
            }
        }

        service.addVehicle(vehicle);
    }

    private static void startVehicle(VehicleService service) {
        int index = getValidIndex(service);
        if (index != -1) service.startVehicle(index);
    }
    private static void stopVehicle(VehicleService service) {
        int index = getValidIndex(service);
        if (index != -1) service.stopVehicle(index);
    }
    private static void showFuelStatus(VehicleService service) {
        int index = getValidIndex(service);
        if (index != -1) service.showFuelStatus(index);
    }

    private static void chargeVehicle(VehicleService service) {
        int index = getValidIndex(service);
        if (index != -1) service.chargeVehicle(index);
    }

    private static void hornVehicle(VehicleService service) {
        int index = getValidIndex(service);
        if (index != -1) service.horn(index);
    }

    private static void playMusic(VehicleService service) {
        int index = getValidIndex(service);
        if (index != -1) service.playMusic(index);
    }

    private static void inspectVehicle(VehicleService service) {
        int index = getValidIndex(service);
        if (index != -1) {
            Vehicle v = service.getVehicle(index);
            if (v != null) Vehicle.vehicleInspection();
        }
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static int getValidIndex(VehicleService service) {
        if (service.getCount() == 0) {
            System.out.println("No vehicles available.");
            return -1;
        }

        service.listVehicles();
        System.out.print("Enter vehicle number (1 to " + service.getCount() + "): ");
        int index = readInt() - 1;

        if (index < 0 || index >= service.getCount()) {
            System.out.println("Invalid vehicle index!");
            return -1;
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.print("Enter number of vehicles to store: ");
        int size = readInt();
        VehicleService service = new VehicleService(size);
        menu(service);
    }
}
