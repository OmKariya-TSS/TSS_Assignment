package testing;
import board.Board;
import board.IBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private IBoard board;

    @BeforeEach
    void setUp() {
        board = new Board(3);
    }

    @Test
    void testInitialize() {
        char[][] grid = board.getBoardState();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                assertEquals('-', grid[i][j]);
            }
        }
    }

    @Test
    void testPlaceMark_Valid() {
        boolean result = board.placeMark(1, 1, 'X');
        assertTrue(result);
        assertEquals('X', board.getBoardState()[1][1]);
    }

    @Test
    void testPlaceMark_CellFilled() {
        board.placeMark(0, 0, 'X');
        boolean result = board.placeMark(0, 0, 'O');
        assertFalse(result);
    }

    @Test
    void testPlaceMark_InvalidPosition() {
        boolean result = board.placeMark(5, 5, 'X');
        assertFalse(result);
    }

    @Test
    void testIsFull_False() {
        assertFalse(board.isFull());
    }

    @Test
    void testIsFull_True() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.placeMark(i, j, 'X');
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    void testIsValidMove() {
        assertTrue(board.isValidMove(1, 1));
        assertFalse(board.isValidMove(-1, 0));
        assertFalse(board.isValidMove(0, -1));
        assertFalse(board.isValidMove(3, 3));
    }

    @Test
    void testGetSize() {
        assertEquals(3, board.getSize());
    }
}
