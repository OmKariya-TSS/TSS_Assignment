package com.tss.assignment1;

import java.sql.SQLOutput;
import java.util.Scanner;

public class CurrencyDenomination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter currency : ");
        long currency=scanner.nextLong();
        if(currency>=50000){
            System.out.println("PleASE ENTER currency less than 50000 enter again" );
            return;
        }
        if(currency%100 != 0){
            System.out.println("tHE currency should be multiple of 100 enter again");
            return;
        }
        int twoThousand =0;
        int fiveHundread =0;
        int twoHundread =0;
        int Hundread =0;
        while(currency>=2000 ){
            currency =(long) currency-2000;
            twoThousand++;
        }
        while(currency>=500){
            currency=(long)currency-500;
            fiveHundread++;
        }
        while(currency>=200){
            currency=(long)currency-200;
            twoHundread++;
        }
        while(currency>=100){
            currency=(long)currency-100;
            Hundread++;
        }
        System.out.println("Hundread: "+Hundread +"Two hUNDREAD :  "+twoHundread + " fiveHundread : "+ fiveHundread+"two thousand :"+twoThousand);

    }
}
