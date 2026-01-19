package com.tss.assignment1;

import java.util.Arrays;
import java.util.Scanner;
public class Prime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        int a = sc.nextInt();
        boolean prime = check(a);
        if(check(a)){
            System.out.println("prime");
        }
        else{
            System.out.println("not prime");
        }

    }
    public static boolean check(int n){
        if(n==0){
            return false;
        }
        else if(n==1){
            return true;
        }
        for(int i=2;i<Math.sqrt(n);i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

}
