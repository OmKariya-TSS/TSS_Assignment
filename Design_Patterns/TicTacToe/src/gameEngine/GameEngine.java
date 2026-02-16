package gameEngine;

import board.IBoard;
import player.IPlayer;

public class GameEngine implements IGameEngine{
    IBoard board;
    IPlayer currentPlayer;
    IPlayer p1;
    IPlayer p2;
    public GameEngine(IBoard board,IPlayer p1,IPlayer p2) {
        this.board = board;
        this.currentPlayer = p1;
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public boolean checkWin(IBoard board, char mark) {

        char[][] grid = board.getBoardState();
        int size = board.getSize();
        //rows
        for(int i=0;i<size;i++){
            boolean isRowWin = true;
            for(int j=0;j<size;j++){
                if(grid[i][j]!=mark){
                    isRowWin = false;
                    break;
                }
            }
            if(isRowWin){
                return true;
            }
        }
        //cols
        for(int i=0;i<size;i++){
            boolean isColWin = true;
            for(int j=0;j<size;j++){
                if(grid[j][i]!=mark){
                    isColWin = false;
                    break;
                }
            }
            if(isColWin){
                return true;
            }
        }
        //diagonals
        boolean diagWin = true;
        for(int i=0;i<size;i++){
            if(grid[i][i]!=mark){
                diagWin = false;
                break;
            }
        }
        if(diagWin){
            return true;
        }

        //anti-diagonals
        boolean antiDiag = true;
        for(int i=0;i<size;i++){
            if(grid[i][size-i-1]!=mark){
                antiDiag = false;
                break;
            }
        }
        if(antiDiag){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkDraw(IBoard board) {
        return board.isFull();
    }

    @Override
    public void switchPlayer() {
        currentPlayer = (currentPlayer==p1)?p2:p1;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }
}
