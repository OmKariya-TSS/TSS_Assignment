package com.tss.assignment1;
import java.util.Scanner;
public class MatrixMenu {
    public static void main(String[] args) {
        System.out.println("Welcome to matrix program");
        System.out.println("please provide two matrix");
        System.out.println("please provide r1 and c1 for creating array1");
        System.out.println("provide r1  :");
        Scanner scanner = new Scanner(System.in);
        int r1 = scanner.nextInt();
        System.out.println("provide c1 :");
        int c1=scanner.nextInt();
        int[][] arr1 = new int[r1][c1];
        System.out.println("please provide r1 and c1 for creating array2");
        System.out.println("provide r2  :");
        int r2 = scanner.nextInt();
        System.out.println("provide c2 :");
        int c2=scanner.nextInt();
        int[][] arr2 = new int[r2][c2];
        int[][] add = new int[r1][c1];
        int[][] mul = new int[r1][c2];
        matrix(arr1,arr2, r1, c1, r2, c2, add, mul);
    }
    public static void matrix(int[][] arr1,int[][] arr2,int r1,int c1,int r2,int c2,int[][] add,int[][] mul) {
        System.out.println("enter 1 for create matrix ");
        System.out.println("enter 2 for display matrix");
        System.out.println("enter 3 for add matrix");
        System.out.println("enter 4 for multiplication");
        System.out.println("enter 5 for checking magical matrix");
        System.out.println("enter 6 for checking prime harmonic matrix");
        System.out.println("enter 7 for exit ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch (n){
            case 1:
                reset(add,r1,c1);
                reset(mul,r1,c2);
                create(arr1, r1,c1);
                create(arr2,r2,c2);
                matrix(arr1, arr2, r1, c1, r2, c2,add,mul);
                break;
            case 3:
                addition(arr1, arr2, r1, c1, r2, c2, add);
                System.out.println("matrix is added");
                matrix(arr1, arr2, r1, c1, r2, c2,add,mul);
                break;
            case 4:
                multiplication(arr1, arr2, r1, c1, r2, c2, mul);
                System.out.println("multiplication is done");
                matrix(arr1, arr2, r1, c1, r2, c2,add,mul);
                break;
            case 2:
                System.out.println("displaying the matrix");
                System.out.println("matrix 1 is ");
                display(arr1,r1,c1);
                System.out.println("matrix 2 is");
                display(arr2,r2,c2);
                if(r1!=r2 && c1!=c2){
                    System.out.println("you cant add the two matrix with different dimensions");
                }else {
                    System.out.println("add matrix is :");
                    display(add, r1, c1);
                }
                if (c1 != r2) {
                    System.out.println("you cant multiply the matrix");
                }else {
                    System.out.println("multiplication matrix is:");
                    display(mul, r2, c2);
                }
                matrix(arr1, arr2, r1, c1, r2, c2,add ,mul );
                break;
            case 5:
                System.out.println("checking if the matrix are magical or not");
                if (magical(arr1, r1, c1)) {
                    System.out.println("matrix 1 is magical");
                } else {
                    System.out.println("matrix 1 is not magical");
                }
                if (magical(arr2, r2, c2)) {
                    System.out.println("matrix 2 is magical");
                } else {
                    System.out.println("matrix 2 is not magical");
                }
                matrix(arr1, arr2, r1, c1, r2, c2,add , mul);
                break;
            case 6:
                System.out.println("checking if matrix are prime harmonic or not");
                if (primeHarmonic(arr1, r1, c1)) {
                    System.out.println("matrix 1 is prime harmonic");
                } else {
                    System.out.println("matrix 1 is not prime harmonic");
                }
                if (primeHarmonic(arr2, r2, c2)) {
                    System.out.println("matrix 2 is prime harmonic");
                } else {
                    System.out.println("matrix 2 is not prime harmonic");
                }
                matrix(arr1, arr2, r1, c1, r2, c2, add, mul);
                break;
            case 7:
                System.out.println("exiting the program because you choose exit");
                break;
            default:
                System.out.println(" please choose between 1 to 7");
                matrix(arr1,arr2,r1,c1,r2,c2,add , mul);
                break;
        }
    }
    private static void create(int[][] arr1,int r1,int c1){
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter values for matrix : ");
        for(int i=0;i<r1;i++){
            for(int j=0;j<c1;j++){
              arr1[i][j]=scanner.nextInt();
            }
        }
    }
    private static void display(int[][] arr1,int r1,int c1){
        for(int i=0;i<r1;i++){
            for(int j=0;j<c1;j++){
                System.out.print(arr1[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    private static void addition(int[][] arr1,int[][] arr2,int r1,int c1,int r2,int c2,int[][] ans){
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    ans[i][j] = arr1[i][j] + arr2[i][j];
                }
            }
    }
    private static void multiplication(int[][] arr1,int[][] arr2,int r1,int r2,int c1,int c2,int[][] ans)
    {
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++) {
                    for (int k = 0; k < c1; k++) {
                        ans[i][j] = ans[i][j] + arr1[i][k] * arr2[k][j];
                    }
                }
            }
    }
    private static boolean primeHarmonic(int[][] arr,int r,int c){
        for (int i = 0; i < r; i++) {
            int count=0;
            for (int j = 0; j < c; j++) {
                if(checkPrime(arr[i][j])){
                    count++;
                }
            }
            if(count!=3) {
                return false;
            }
        }
        return true;
    }
    private static boolean checkPrime(int n){
        if(n==0){
            return false;
        }
        else if(n==1){
            return true;
        }
        for(int i=2;i<(int)Math.sqrt(n);i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
    private static boolean magical(int[][] arr1,int r1,int c1){
        if(r1!=c1){
            return false;
        }
        int magicSum=0;
        int mainDiagSum=0;
        int otherDiag=0;
        for (int i = 0; i < r1; i++) {
            magicSum += arr1[0][i];
            mainDiagSum += arr1[i][i];
            otherDiag += arr1[i][r1-i-1];
        }
        if(mainDiagSum!=magicSum || otherDiag!=magicSum){
            return false;
        }
        for (int i = 0; i < r1; i++) {
            int rowSum=0;
            int colSum=0;
            for (int j = 0; j < c1; j++) {
                rowSum = rowSum+arr1[i][j];
                colSum=colSum+arr1[j][i];
            }
            if(rowSum!=magicSum || colSum!=magicSum){
                return false;
            }
        }
        return true;
    }
    private static void reset(int[][] arr,int r,int c){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                arr[i][j] = 0;
            }
        }
    }
}