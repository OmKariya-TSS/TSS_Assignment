package com.tss.assignment1;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int max = 100;
        int min = 1;
        boolean answer = true;
        while (answer) {
            boolean won = false;
            int count = 0;
            int random_number = random.nextInt(max - min + 1) + min;
            while (!won && count < 5) {
                System.out.println(random_number);
                System.out.println("enter the guessed number : ");
                int number = scanner.nextInt();
                if (number < random_number) {
                    System.out.println("too low");
                } else if (number > random_number) {
                    System.out.println("the number is too high");
                } else {
                    System.out.println("you guessed the right number");
                    won = true;
                }
                count++;
            }
            if (count==5) {
                System.out.println("You lost max tries reached");
                System.out.println("The number was: " + random_number);
            }
            System.out.println("you want to play again ?");
            answer = scanner.nextBoolean();
        }
    }
}
