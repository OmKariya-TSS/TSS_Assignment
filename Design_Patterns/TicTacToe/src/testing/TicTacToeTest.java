package testing;
import board.IBoard;
import gameEngine.IGameEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.IPlayer;
import service.TicTacToeService;

import static org.mockito.Mockito.*;

public class TicTacToeTest {

    private IBoard board;
    private IGameEngine engine;
    private IPlayer p1;
    private IPlayer p2;
    private TicTacToeService service;

    @BeforeEach
    void setUp() {
        board = mock(IBoard.class);
        engine = mock(IGameEngine.class);
        p1 = mock(IPlayer.class);
        p2 = mock(IPlayer.class);

        service = new TicTacToeService(board, engine, p1, p2);
    }
    @Test
    void testMakeMove_Valid() {

        when(engine.getCurrentPlayer()).thenReturn(p1);
        when(p1.getMark()).thenReturn('X');
        when(board.isValidMove(0,0)).thenReturn(true);
        when(board.placeMark(0,0,'X')).thenReturn(true);
        when(engine.checkWin(board,'X')).thenReturn(false);
        when(engine.checkDraw(board)).thenReturn(false);

        service.makeMove(0,0);

        verify(engine).switchPlayer();
    }
    @Test
    void testMakeMove_Win() {

        when(engine.getCurrentPlayer()).thenReturn(p1);
        when(p1.getMark()).thenReturn('X');
        when(p1.getName()).thenReturn("Player1");
        when(board.isValidMove(0,0)).thenReturn(true);
        when(board.placeMark(0,0,'X')).thenReturn(true);
        when(engine.checkWin(board,'X')).thenReturn(true);

        service.makeMove(0,0);

        verify(engine, never()).switchPlayer();
    }
    @Test
    void testMakeMove_Draw() {

        when(engine.getCurrentPlayer()).thenReturn(p1);
        when(p1.getMark()).thenReturn('X');
        when(board.isValidMove(0,0)).thenReturn(true);
        when(board.placeMark(0,0,'X')).thenReturn(true);
        when(engine.checkWin(board,'X')).thenReturn(false);
        when(engine.checkDraw(board)).thenReturn(true);

        service.makeMove(0,0);

        verify(engine, never()).switchPlayer();
    }



}
