package com.tss.assignment1;

import java.util.Scanner;

public class Product {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter size : ");
        int n = scanner.nextInt();
        int[] array= new int[n];
        int commonProd =1;
        int zeroCount=0;
        for(int i=0;i<n;i++){
            System.out.println("enter "+ i +"th element :");
            array[i]=scanner.nextInt();
            if(array[i]!=0) commonProd= commonProd* array[i];
            else zeroCount++;
        }
        int[] prod = new int[n];
        for(int i=0;i<n;i++){
            if(array[i]!=0) prod[i] = commonProd/array[i];
            else if(zeroCount==1) prod[i]=commonProd;
        }

        if(zeroCount>1){
            for(int i=0;i<n;i++){
                System.out.print(prod[i]+" ");
            }
            return;
        }
        for(int i=0;i<n;i++){
            System.out.print(prod[i]+" ");
        }
    }
}
