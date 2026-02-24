package facade;

import board.Board;
import board.IBoard;
import gameEngine.GameEngine;
import gameEngine.IGameEngine;
import player.HumanPlayer;
import player.IPlayer;
import service.ITicTacToe;
import service.TicTacToeService;



public class TicTacToeFacade {
    public void startNewGame(String player1, String player2, int boardSize) {
        IBoard board = new Board(boardSize);
        IPlayer p1 = new HumanPlayer(player1, 'X');
        IPlayer p2 = new HumanPlayer(player2, 'O');
        IGameEngine engine = new GameEngine(board, p1, p2);
        ITicTacToe game = new TicTacToeService(board, engine, p1, p2);
        game.startGame();
    }
}