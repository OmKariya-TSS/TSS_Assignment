package com.tss.assignment1;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r;
        int rev=0;
        int a = sc.nextInt();
        int temp=a;
        while(temp!=0){
            r = temp%10;
            rev= rev*10+r;
            temp/=10;
        }
        if(rev==a){
            System.out.println("The number is palindrome");
        }
        else{
            System.out.println("The nymber is not palindrome");
        }
    }
}

