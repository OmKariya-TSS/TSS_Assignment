package com.tss.assignment1;

import java.util.Scanner;


public class Armstrong {
    public static void main(String[] args) {
        int num;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the  number : ");
        num= sc.nextInt();
        int numOfDig =0;
        int sum=0;
        int temp=num;
        while(temp!=0){
            numOfDig++;
            temp/=10;
        }
        temp=num;
        while(temp!=0){
            int r=temp%10;
            sum+=Math.pow(r,numOfDig);
            temp/=10;
        }
        if(sum == num){
            System.out.println("armstrong");
        }
        else{
            System.out.println("Not aarmsrong");
        }

    }
}
