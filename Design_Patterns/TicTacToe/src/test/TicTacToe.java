package test;

import board.Board;
import board.IBoard;
import facade.ITicTacToe;
import facade.TicTacToeFacade;
import gameEngine.GameEngine;
import gameEngine.IGameEngine;
import player.HumanPlayer;
import player.IPlayer;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            System.out.println("===== TIC TAC TOE =====");
            System.out.print("Enter Player 1 Name: ");
            String name1 = scanner.next();
            System.out.print("Enter Player 2 Name: ");
            String name2 = scanner.next();
            System.out.println("Enter board size :");
            int n = scanner.nextInt();
            IBoard board = new Board(n);
            IPlayer p1 = new HumanPlayer(name1, 'X');
            IPlayer p2 = new HumanPlayer(name2, 'O');
            IGameEngine engine = new GameEngine(board, p1, p2);
            ITicTacToe game = new TicTacToeFacade(board, engine, p1, p2);
            game.startGame();
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
