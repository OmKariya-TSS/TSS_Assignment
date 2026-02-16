package gameEngine;

import board.IBoard;
import player.IPlayer;

public interface IGameEngine {
    boolean checkWin(IBoard board, char mark);
    boolean checkDraw(IBoard board);
    void switchPlayer();
    IPlayer getCurrentPlayer();
}
