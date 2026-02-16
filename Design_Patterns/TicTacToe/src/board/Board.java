package board;

public class Board implements IBoard{
    private char[][] grid;
    private int size;
    private static final char EMPTY = '-';
    private int[][] numbersGrid;

    public Board(int size){
        this.size = size;
        this.grid= new char[size][size];
        this.numbersGrid = new int[size][size];
        initialize();
    }
    @Override
    public void initialize() {
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                grid[i][j]=EMPTY;
            }
        }
        int count=1;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                int c=1;
                if(c==size){
                    break;
                }
               numbersGrid[i][j]=count++;
                c++;
            }
            System.out.println();

        }
    }
    @Override
    public boolean placeMark(int row, int col, char mark) {

        if(!isValidMove(row, col)) {
            System.out.println("invalid move");
            return false;
        }
        if(grid[row][col] != EMPTY) {
            return false;
        }
        if(grid[row][col]=='X' && mark=='O'){
            System.out.println("cell filled by player O");
            return false;
        }
        if(grid[row][col]=='O' && mark=='X'){
            System.out.println("cell filled by player X");
            return false;
        }

        numbersGrid[row][col] = -1;
        grid[row][col] = mark;
        return true;
    }


    @Override
    public boolean isFull() {
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(grid[i][j]==EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public char[][] getBoardState() {
        return grid;
    }

    @Override
    public int getSize() {
        return size;
    }

    public boolean isValidMove(int row,int col){
        return row>=0 && col>=0 && row<size && col<size;
    }
}
