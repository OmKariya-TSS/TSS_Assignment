package com.tss.assignment1;

import java.util.*;

public class SmartCity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to smart city utility control system");
        smartCity();
    }
    enum PlanType {
        STUDENT,
        HOME,
        BUSINESS
    }
    enum WaterType {
        RESIDENTIAL,
        SOCIETY,
        FACTORY
    }
    public static void smartCity(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 for Electricity service");
        System.out.println("enter 2 for Water service");
        System.out.println("enter 3 for Internet Service");
        System.out.println("enter 4 for Exit");
        int number = scanner.nextInt();
        double bill_amount=0;
        double energy_charge=0;
        switch(number){
            case 1:
                System.out.println(" you are under electricity service");
                electric_method();
                break;
            case 2:
                System.out.println(" you are under water service");
                water_method();
                break;
            case 3:
                System.out.println("You are under internet services :");
                internet_method();
                break;
            case 4:
                System.out.println("you are on the exit so program stopped");
                break;
            default:
                System.out.println("enter number between 1 to 4");
                smartCity();
        }
    }
    public static void electric_method(){
        Scanner scanner = new Scanner(System.in);
        double bill_amount=0;
        double energy_charge=0;
        System.out.println("Enter 1 for domestic connection ");
        System.out.println("enter 2 for commercial connection");
        System.out.println("enter 3 for Industrial connection");
        System.out.println("enter 4 for back option");
        int electricity = scanner.nextInt();
        int fixed_charge_domestic = 50;
        int fixed_charge_commercial = 150;
        int fixed_charge_industrial = 500;
        switch(electricity){
            case 1:
                String connection = "domestic";
                System.out.println("you are under domestic");
                int unites_consumed = ask_units();
                if(unites_consumed>=0 && unites_consumed<=100){
                    energy_charge =(double) energy_charge + unites_consumed*2;
                }
                else if(unites_consumed>=101 && unites_consumed<=300){
                    energy_charge = (double) energy_charge + unites_consumed*3;
                }
                else if(unites_consumed>=301){
                    energy_charge =(double) energy_charge + unites_consumed*5;
                }
                bill_amount=(double) energy_charge+ fixed_charge_domestic;
//                System.out.println("connection type : domestic connection");
//                System.out.println("Units consumed : "+ unites_consumed);
//                System.out.println("Energy charge : "+energy_charge);
//                System.out.println("fixed charge : "+fixed_charge_domestic);
                double surcharge=0;
                if(bill_amount>=2000){
                    surcharge = bill_amount*10/100;
                    bill_amount=bill_amount+ surcharge;
                    System.out.println("surcharge :"+surcharge);
                }
                printElectricityBill(connection,unites_consumed,energy_charge,fixed_charge_domestic,surcharge,bill_amount);
//                System.out.println("total bill  "+bill_amount);
                smartCity();
                break;
            case 2:
                connection = "commericial";
                System.out.println("you are under commercial");
                unites_consumed = ask_units();
                if(unites_consumed>=0 && unites_consumed<=200){
                    energy_charge =(double) energy_charge + unites_consumed*5;
                }
                else if(unites_consumed>=201 && unites_consumed<=500){
                    energy_charge = (double) energy_charge + unites_consumed*7;
                }
                else if(unites_consumed>=501){
                    energy_charge = (double) energy_charge + unites_consumed*10;
                }
                surcharge =0;
                bill_amount=(double)  energy_charge+fixed_charge_commercial;
//                System.out.println("connection type : commercial connection");
//                System.out.println("Units consumed : "+ unites_consumed);
//                System.out.println("Energy charge : "+energy_charge);
//                System.out.println("fixed charge : "+fixed_charge_commercial);
//                System.out.println("total bill  "+bill_amount);
                printElectricityBill(connection,unites_consumed,energy_charge,fixed_charge_commercial,surcharge,bill_amount);
                smartCity();
                break;
            case 3:
                connection = "industrial";
                System.out.println("you are under indsutrial");
                unites_consumed = ask_units();
                energy_charge= (double) energy_charge+ unites_consumed*12;
                bill_amount=(double) energy_charge+fixed_charge_industrial;

//                System.out.println("connection type : industrial connection");
//                System.out.println("Units consumed : "+ unites_consumed);
//                System.out.println("Energy charge : "+energy_charge);
//                System.out.println("fixed charge : "+fixed_charge_industrial);
                double rebate=0;
                if(unites_consumed>100){
                    rebate = bill_amount * 15/100;
                    bill_amount = bill_amount - rebate;
                    System.out.println("rebate "+rebate);
                }
                printElectricityBill(connection,unites_consumed,energy_charge,fixed_charge_industrial,rebate,bill_amount);
                smartCity();
                break;
            case 4:
                System.out.println("you have chosen back so going back ");
                smartCity();
                break;
            default:
                System.out.println("enter number between 1 to 4");
                electric_method();
        }
    }
    public static void water_method(){
        Scanner scanner = new Scanner(System.in);
        double bill_amount=0;
        double energy_charge=0;
        System.out.println("Enter 1 for residential ");
        System.out.println("enter 2 for society ");
        System.out.println("enter 3 for factory ");
        System.out.println("enter 4 for back option");
        int water_service = scanner.nextInt();
        switch(water_service){
            case 1:
                System.out.println("you are under residential ");
                System.out.println("Enter number of persons:");
                int persons = scanner.nextInt();
                printWaterBill(WaterType.RESIDENTIAL, persons);
                smartCity();
                break;
            case 2 :
                System.out.println("you are under society");
                System.out.println("enter number of tapes : ");
                int tapes = scanner.nextInt();
                printWaterBill(WaterType.SOCIETY, tapes);
                smartCity();
                break;
            case 3:
                System.out.println("you are under factory");
                System.out.println("Enter number of machines:");
                int machines = scanner.nextInt();
                printWaterBill(WaterType.FACTORY, machines);
                smartCity();
                break;
            case 4:
                smartCity();
                break;
            default:
                System.out.println("enter between 1 to  4 only");
                water_method();
        }
    }
    public static void internet_method(){
        Scanner scanner = new Scanner(System.in);
        double bill_amount=0;
        double energy_charge=0;
        System.out.println("Enter 1 for student plan ");
        System.out.println("enter 2 for home plan ");
        System.out.println("enter 3 for business plan ");
        System.out.println("enter 4 for back option");
        int internet = scanner.nextInt();
        int duration ;
        switch(internet){
            case 1:
                System.out.println("you are under student plan");
                duration = ask_duration();
                printInternetBill(PlanType.STUDENT, duration);
                smartCity();
                break;
            case 2:
                System.out.println("you are under home plan");
                duration = ask_duration();
                printInternetBill(PlanType.HOME, duration);
                smartCity();
                break;
            case 3:
                System.out.println("you are under business plan");
                duration = ask_duration();
                printInternetBill(PlanType.HOME, duration);
                smartCity();
                break;
            case 4:
                System.out.println("returning back to main menu");
                smartCity();
                break;
            default:
                System.out.println("enter number between 1 to 4 only");
                internet_method();
        }
    }
    public static int ask_duration() {
        Scanner scanner = new Scanner(System.in);
        int dur;
        while (true) {
            System.out.println("Enter duration (1, 3, or 6): ");
            dur = scanner.nextInt();
            if (dur == 1 || dur == 3 || dur == 6) {
                return dur;
            } else {
                System.out.println("Invalid duration. Please try again.");
            }
        }
    }

    public static int ask_units() {
        Scanner scanner = new Scanner(System.in);
        int units;
        while (true) {
            System.out.println("Enter units consumed : ");
            units = scanner.nextInt();
            if (units >= 0) {
                return units;
            } else {
                System.out.println("Enter only positive units. Try again.");
            }
        }
    }
    public static void printElectricityBill(
            String connection,
            int units_consumed,
            double energycharge,
            int fixed_charge,
            double surchargeOrRebate,
            double totalbill
    ){
        System.out.println("Connection Type :"+connection);
        System.out.println("Units consumed : "+units_consumed);
        System.out.println("energy charge : "+energycharge);
        System.out.println("fixed charge :"+fixed_charge);
        System.out.println("surcharge or rabate is :"+surchargeOrRebate);
        System.out.println("total bill is : "+totalbill);
    }
    public static void printInternetBill(PlanType plan, int duration) {
        double billAmount = 0;
        switch (plan) {
            case STUDENT:
                if (duration == 1) billAmount = 299;
                else if (duration == 3) billAmount = 799;
                else if (duration == 6) billAmount = 1499;
                break;
            case HOME:
                if (duration == 1) billAmount = 499;
                else if (duration == 3) billAmount = 1399;
                else if (duration == 6) billAmount = 2699;
                break;
            case BUSINESS:
                if (duration == 1) billAmount = 999;
                else if (duration == 3) billAmount = 2799;
                else if (duration == 6) billAmount = 5499;
                break;
        }
        if (duration == 6) {
            System.out.println("Bill without discount: " + billAmount);
            double discount = billAmount * 0.05;
            billAmount -= discount;
            System.out.println("Bill with discount: " + billAmount);
        } else {
            System.out.println("Bill is: " + billAmount);
        }
    }
    public static void printWaterBill(WaterType type, int quantity) {
        double billAmount = 0;
        switch(type) {
            case RESIDENTIAL:
                billAmount = quantity * 30;
                break;
            case SOCIETY:
                billAmount = quantity * 25;
                break;
            case FACTORY:
                billAmount = quantity * 100;
                break;
        }
        System.out.println("Total bill: " + billAmount);
        if (billAmount > 3000) {
            double tax = billAmount * 0.08;
            billAmount += tax;
            System.out.println("Total bill with tax: " + billAmount);
        }
    }
}