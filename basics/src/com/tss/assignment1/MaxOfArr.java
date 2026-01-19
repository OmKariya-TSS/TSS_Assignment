package com.tss.assignment1;

import java.util.Scanner;

public class MaxOfArr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter size : ");
        int n = scanner.nextInt();
        int[] array= new int[n];
        for(int i=0;i<n;i++){
            System.out.println("enter "+ i+1 +"th element :");
            array[i]=scanner.nextInt();
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            System.out.println(array[i]);
        }
        for(int i=0;i<n;i++){
            if(array[i]>max){
                max=array[i];
            }
        }
        System.out.println("max is :"+max);



    }
}
