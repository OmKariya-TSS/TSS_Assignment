package com.tss.assignment1;

import java.util.Scanner;

public class Perfect {
    public static void main(String[] args) {
        int num;
        int sum=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter nymber:");
        num = sc.nextInt();
        for(int i=1;i<num;i++){
            if(num%i==0){
                sum=sum+i;
            }
        }
        if(sum==num){
            System.out.println("The number is perfect");
        }
        else{
            System.out.println("THe numnber is not perfect");
        }
    }

}
