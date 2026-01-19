package com.tss.assignment1;

import java.util.Scanner;


public class FindMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a,b,c;
        System.out.println("Enter first number : ");
        a = sc.nextInt();
        System.out.println("Enter second number : ");
        b = sc.nextInt();
        System.out.println("Enter third number : ");
        c = sc.nextInt();
        if(a>b){
            if(a>c){
                System.out.println("the max number is is  "+a );
            }
            else{
                System.out.println("the max nunber is "+c);
            }
        }
        else{
            if(b>c){
                System.out.println("the max nunmber is "+b);
            }else{
                System.out.println("the max number is "+c);
            }
        }
    }
}
