package com.tss.assignment1;

import java.util.Scanner;

public class RotateRight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter size : ");
        int n = scanner.nextInt();
        int[] array= new int[n];
        for(int i=0;i<n;i++){
            System.out.println("enter "+ i+1 +"th element :");
            array[i]=scanner.nextInt();
        }
        for(int i=0;i<n;i++){
            System.out.println(array[i]);
        }
        System.out.println("enter p to rotate");
        int p = scanner.nextInt();
        p = p%n;
        reverse(0,n-1,array);
        reverse(0,p-1,array);
        reverse(p,n-1,array);
        System.out.println("rotated array is :");
        for(int i=0;i<n;i++){
            System.out.print(array[i] + " ");
        }

    }
    private static void reverse(int start,int end,int[] arr){
        while(start<end){
            int temp = arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
    }
}
