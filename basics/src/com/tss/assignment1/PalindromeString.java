package com.tss.assignment1;

import java.util.Scanner;

public class PalindromeString {
    public static void main(String[] args) {
        System.out.println("enter first string");
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        boolean checked = check(first);
        if(checked){
            System.out.println("the string is palindrome");
        }
        else{
            System.out.println("not palindrom");
        }
        if(checkUsingBuilder(first)){
            System.out.println("palindrome using builder");
        }
        else{
            System.out.println("not palindrmoe using builder");
        }
    }
    private static boolean check(String string){
        int left=0;
        int right = string.length()-1;
        while(left<right){
            if((string.charAt(right)) != string.charAt(left)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    private static boolean checkUsingBuilder(String string){
        StringBuilder sb = new StringBuilder(string);
        return (sb.reverse().toString()).equals(string);
    }
}
