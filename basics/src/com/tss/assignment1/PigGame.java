package com.tss.assignment1;

import java.util.Random;
import java.util.Scanner;

public class PigGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to pig game");
        boolean play = true;
        do {
            pig();
            System.out.println("you want to play again?");
            play = scanner.nextBoolean();
        }while(play);
    }
    public static void pig(){
        int turns =1;
        int score=0;
        boolean won = false;
        Scanner scanner = new Scanner(System.in);
        while(turns<=5 && !won) {
            System.out.println("turn number :"+ turns);
            System.out.println("enter true for roll and false for hold");
            boolean ans = scanner.nextBoolean();
            if (ans) {
                int roll_score = roll();
                System.out.println("dice is at : "+roll_score);
                if (roll_score == 1) {
                    score=0;
                    System.out.println("score is set to zero");
                    turns++;
                }else {
                    score = score + roll_score;
                }
                System.out.println("score :"+score);
                if (score >= 20) {
                    System.out.println("you won the game in " + turns + "tries");
                    won = true;
                }
            }
            else{
                System.out.println("turn is increased new turn");
                turns++;
                System.out.println("current score is : "+score);
                System.out.println("turn number : "+turns);
            }
        }
        if(!won){
            System.out.println("you did not won");
        }
    }
    public static int roll(){
        Random random = new Random();
        int rolls = random.nextInt(6)+1;
        return rolls;
    }
}
