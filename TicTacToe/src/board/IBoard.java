package board;

public interface IBoard {
    void initialize();
    boolean placeMark(int row, int col, char mark);
    boolean isFull();
    char[][] getBoardState();
    int getSize();
    boolean isValidMove(int row,int col);
}

