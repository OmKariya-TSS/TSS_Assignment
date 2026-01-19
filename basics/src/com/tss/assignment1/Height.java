package com.tss.assignment1;

import java.util.Scanner;

public class Height {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bill=0;
        System.out.println("Enter height");
        float height =scanner.nextFloat();
        if(height>120){
            System.out.println("You can ride");
            System.out.println("Enter age");
            int age = scanner.nextInt();
            if(age<12){
                bill+=55;
            }
            if(age>=12 && age<18){
                bill+=57;
            }
            if(age>18){
                bill+=512;

            }
            if(age>45 && age<=55){
                bill+=80;
            }
            System.out.println("You want photos? enter 0 or 1");
            int photo = scanner.nextInt();
            if(photo == 1){
                bill+=53;
            }
            bill=bill*5;
            System.out.println("bill is "+bill);
        }
        else{
            System.out.println("You cant ride sorry ");
        }
    }
}
