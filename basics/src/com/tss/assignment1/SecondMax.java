package com.tss.assignment1;

import java.util.Scanner;

public class SecondMax {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter size : ");
            int n = scanner.nextInt();
            int[] array= new int[n];
            for(int i=0;i<n;i++){
                System.out.println("enter "+ i+1 +"th element :");
                array[i]=scanner.nextInt();
            }
            int max = array[0];
            int secondMax=array[0];
            for(int i=0;i<n;i++){
                if(array[i]>max){
                    secondMax=max;
                    max=array[i];
                }
                else if(secondMax<array[i]){
                    secondMax = array[i];
                }
            }
        System.out.println("second max is : "+secondMax);
        }
}
