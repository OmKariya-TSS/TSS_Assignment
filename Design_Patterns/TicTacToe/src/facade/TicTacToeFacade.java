package facade;

import board.IBoard;
import gameEngine.IGameEngine;
import player.IPlayer;

import java.util.Scanner;

public class TicTacToeFacade implements ITicTacToe{
    private IBoard board;
    private IGameEngine engine;
    private IPlayer player1;
    private IPlayer player2;
    private boolean gameOver;
    private Scanner scanner = new Scanner(System.in);

    public TicTacToeFacade(IBoard board,IGameEngine engine,IPlayer p1, IPlayer p2){
        this.board =board;
        this.engine =engine;
        this.player1 = p1;
        this.player2 = p2;
    }
    @Override
    public void startGame() {
        board.initialize();
        gameOver = false;
        while(!gameOver){
            IPlayer currentPlayer = engine.getCurrentPlayer();
            displayBoard();
            System.out.println("Current player : "+currentPlayer.getName() + "'s turn");
            System.out.println("enter position");
            int n = scanner.nextInt();
            int r = (n-1)/3;
            int c = (n-1)%3;
            if(board.isValidMove(r,c)){
                makeMove(r,c);
            }
        }
    }

    @Override
    public void makeMove(int row, int col) {
        IPlayer currentPlayer = engine.getCurrentPlayer();
        if (!board.placeMark(row, col, currentPlayer.getMark())) {
            System.out.println("Cell already filled! Try again.");
            return;
        }
        if(!board.isValidMove(row,col)){
            System.out.println("invalid move");
            return;
        }
        board.placeMark(row,col, currentPlayer.getMark());
        if(engine.checkWin(board, currentPlayer.getMark())){
            displayBoard();
            System.out.println("Current player : "+currentPlayer.getName()+" Wins");
            gameOver = true;
            return;
        }
        if(engine.checkDraw(board)){
            displayBoard();
            System.out.println("Game tied");
            gameOver = true;
            return;
        }
        engine.switchPlayer();
    }

    @Override
    public void displayBoard() {
        char[][] grid = board.getBoardState();
        int size = board.getSize();
        System.out.print("┌");
        for (int i = 0; i < size; i++) {
            System.out.print("───");
            if (i < size - 1) System.out.print("┬");
        }
        System.out.println("┐");

        for (int i = 0; i < size; i++) {
            System.out.print("│");
            for (int j = 0; j < size; j++) {
                System.out.print(" " + grid[i][j] + " ");
                if (j < size - 1) System.out.print("│");
            }
            System.out.println("│");

            if (i < size - 1) {
                System.out.print("├");
                for (int j = 0; j < size; j++) {
                    System.out.print("───");
                    if (j < size - 1) System.out.print("┼");
                }
                System.out.println("┤");
            }
        }
        System.out.print("└");
        for (int i = 0; i < size; i++) {
            System.out.print("───");
            if (i < size - 1) System.out.print("┴");
        }
        System.out.println("┘");
    }

}
