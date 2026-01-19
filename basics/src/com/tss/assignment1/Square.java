package com.tss.assignment1;

import java.util.Scanner;

public class Square {
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
            System.out.print(array[i]+" ");
        }
        System.out.println("output array is : " );
        squareSort(array,n);
    }
    private static void squareSort(int[] arr,int size){
        int[] array=new int[size];
        int left = 0;
        int right = size-1;
        int index=size-1;
        while(left<right){
            if(arr[left]*arr[left]>arr[right]*arr[right]){
                array[index]=arr[left]*arr[left];
                left++;
            }
            else{
                array[index]=arr[right]*arr[right];
                right--;
            }
            index--;
        }
        for(int i=0;i<size;i++){
            System.out.print(array[i]+" ");
        }
    }
}
