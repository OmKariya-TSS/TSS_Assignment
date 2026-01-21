package com.tss.assignment1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EncodeDecode {

    private static String encode(String str, Map<Character, Character> map) {
        str = str.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (map.containsKey(current)) {
                sb.append(map.get(current));
            }
            else if (current >= 'a' && current <= 'z') {
                char shifted = (char) ((current - 'a' + 1) % 26 + 'a');
                sb.append(shifted);
            }
            else {
                sb.append(current);
            }
        }
        return sb.reverse().toString();
    }

    private static String decode(String str, Map<Character,Character> map){
        StringBuilder sb=new StringBuilder(str);
        StringBuilder newSB = new StringBuilder();
        str=sb.reverse().toString();
        for(int i=0;i<str.length();i++){
            char current = str.charAt(i);
            if (map.containsKey(current)) {
                newSB.append(map.get(current));
            }
            else if (current >= 'a' && current <= 'z') {
                char shifted = (char) ((current - 'a' - 1) % 26 + 'a');
                newSB.append(shifted);
            }
            else {
                newSB.append(current);
            }
        }
        return newSB.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a string to encode and decode");
        String str = scanner.nextLine();
        Map<Character,Character> map = new HashMap<>();
        map.put('a','@');
        map.put('e','#');
        map.put('i','!');
        map.put('o','*');
        map.put('u','$');
        Map<Character,Character> rev = new HashMap<>();
        rev.put('@','a');
        rev.put('#','e');
        rev.put('!','i');
        rev.put('*','o');
        rev.put('$','u');
        String encoded = encode(str,map);
        String decoded = decode(encoded,rev);
        System.out.println("encoded :"+encoded);
        System.out.println("decoded :"+decoded);
    }
}
