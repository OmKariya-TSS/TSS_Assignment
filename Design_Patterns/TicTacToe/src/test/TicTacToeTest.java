package test;

import facade.TicTacToeFacade;

import java.util.Scanner;

public class TicTacToeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToeFacade facade = new TicTacToeFacade();
        boolean playAgain = true;
        while (playAgain) {
            System.out.println("===== TIC TAC TOE =====");
            System.out.print("Enter Player 1 Name: ");
            String name1 = scanner.next();
            System.out.print("Enter Player 2 Name: ");
            String name2 = scanner.next();
            System.out.println("Enter board size:");
            int n = scanner.nextInt();
            facade.startNewGame(name1, name2, n);
            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
            System.out.println();
        }
        System.out.println("Thanks for playing!");
    }
}