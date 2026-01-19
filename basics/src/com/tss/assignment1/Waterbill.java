package com.tss.assignment1;

import java.util.Scanner;

public class Waterbill {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no. of units consumed : ");
        int num_of_units = scanner.nextInt();
        int meter_charge=75;
        int charge;
        if(num_of_units<=100){
            charge = num_of_units*5;
        }else{
            if(num_of_units<=250){
                charge=num_of_units*10;
            }
            else{
                charge=num_of_units*20;
            }
        }
        int total_water_bill = charge+meter_charge;
        System.out.println("Total water bill is :"+total_water_bill);
    }
}
