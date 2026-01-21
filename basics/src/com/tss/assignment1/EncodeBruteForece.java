package com.tss.assignment1;

import java.util.Scanner;
public class EncodeBruteForece {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter string to encode: ");
            String message = scanner.nextLine();
            String encoded = encoder(message);
            System.out.println("Final Encoded Output: " + encoded);
            String decoded = decoder(encoded);
            System.out.println("Decoded Output: " + decoded);

        }
        private static String encoder(String message) {
            message = message.toLowerCase();
            StringBuilder result = new StringBuilder();
            for(char c : message.toCharArray()) {
                if(c == 'a') result.append('@');
                else if (c == 'e') result.append('#');
                else if (c == 'i') result.append('!');
                else if (c == '0') result.append('*');
                else if (c == 'u') result.append('$');
                else if (c == ' ') result.append(' ');
                else if (c >= 'a' && c <= 'z') {
                    if(c == 'z') result.append('a');
                    else result.append((char)(c + 1));
                }
                else {
                    result.append(c);
                }
            }
            return result.reverse().toString();
        }
        private static String decoder(String message) {
            message = message.toLowerCase();
            StringBuilder result = new StringBuilder();
            for(char c : message.toCharArray()) {
                if(c == '@') result.append('a');
                else if (c == '#') result.append('e');
                else if (c == '!') result.append('i');
                else if (c == '*') result.append('o');
                else if (c == '$') result.append('u');
                else if (c == ' ') result.append(' ');
                else if (c >= 'a' && c <= 'z') {
                    if(c == 'a') result.append('z');
                    else result.append((char)(c - 1));
                }
                else {
                    result.append(c);
                }
            }
            return result.reverse().toString();
        }
}
