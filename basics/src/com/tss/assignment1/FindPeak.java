package com.tss.assignment1;

import java.util.Scanner;

public class FindPeak {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter size : ");
        int n = scanner.nextInt();
        int[] array= new int[n];
        for(int i=0;i<n;i++){
            System.out.println("enter "+ i+1 +"th element :");
            array[i]=scanner.nextInt();
        }
        int peak = findPeak(n,array);
        if(peak==0) System.out.println("peak element not available");
        else System.out.println("peak is :"+peak);

    }
    private static int findPeak(int size,int[] arr){
        if(arr[0]>arr[1]) return arr[0];
        else if(arr[size-1]>arr[size-2]) return arr[size-1];
        for(int i=1;i<size-1;i++){
            if(arr[i-1]<=arr[i] && arr[i]>=arr[i+1]){
                return arr[i];
            }
        }
        return 0;
    }
}
