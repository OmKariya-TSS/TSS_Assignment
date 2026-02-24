package testing;
import gameEngine.GameEngine;
import board.IBoard;
import org.junit.jupiter.api.Test;
import player.IPlayer;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameEngineTest {

    private IBoard board;
    private IPlayer p1;
    private IPlayer p2;
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        board = mock(IBoard.class);
        p1 = mock(IPlayer.class);
        p2 = mock(IPlayer.class);

        engine = new GameEngine(board, p1, p2);
    }
    @Test
    void testCheckWin_RowWin() {

        char[][] grid = {
                {'X','X','X'},
                {'O','-','O'},
                {'-','-','-'}
        };
        when(board.getBoardState()).thenReturn(grid);
        when(board.getSize()).thenReturn(3);
        assertTrue(engine.checkWin(board, 'X'));
    }
    @Test
    void testCheckWin_ColumnWin() {

        char[][] grid = {
                {'O','X','-'},
                {'O','X','-'},
                {'O','-','X'}
        };
        when(board.getBoardState()).thenReturn(grid);
        when(board.getSize()).thenReturn(3);
        assertTrue(engine.checkWin(board, 'O'));
    }
    @Test
    void testCheckWin_DiagonalWin() {

        char[][] grid = {
                {'X','O','-'},
                {'-','X','O'},
                {'-','-','X'}
        };
        when(board.getBoardState()).thenReturn(grid);
        when(board.getSize()).thenReturn(3);
        assertTrue(engine.checkWin(board, 'X'));
    }
    @Test
    void testCheckWin_AntiDiagonalWin() {
        char[][] grid = {
                {'-','-','O'},
                {'-','O','-'},
                {'O','-','-'}
        };
        when(board.getBoardState()).thenReturn(grid);
        when(board.getSize()).thenReturn(3);
        assertTrue(engine.checkWin(board, 'O'));
    }
    @Test
    void testCheckWin_NoWin() {
        char[][] grid = {
                {'X','O','X'},
                {'O','X','O'},
                {'O','X','O'}
        };
        when(board.getBoardState()).thenReturn(grid);
        when(board.getSize()).thenReturn(3);
        assertFalse(engine.checkWin(board, 'X'));
    }
    @Test
    void testCheckDraw() {
        when(board.isFull()).thenReturn(true);
        assertTrue(engine.checkDraw(board));
    }
    @Test
    void testSwitchPlayer() {
        assertEquals(p1, engine.getCurrentPlayer());
        engine.switchPlayer();
        assertEquals(p2, engine.getCurrentPlayer());
        engine.switchPlayer();
        assertEquals(p1, engine.getCurrentPlayer());
    }


}