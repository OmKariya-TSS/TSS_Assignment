interface ITicTacToe{
    -void startgame();
    -void makeMove();
    -void displayBoard();
    -boolean isGameOver();
}

concrete TicTacToe class implements ITicTacToe

interface Iboard{
    -void initialize();
    -boolean placeMark(row,col,board);
    -boolean isCellEmpty(row,col);
    -char[][] getBoardState();
    -void display();
}
->concrete implementing board class 

player{
    String getName();
    Char getMark();
    void MakeMove();
} 
-player,ai , human etc implementing i player

