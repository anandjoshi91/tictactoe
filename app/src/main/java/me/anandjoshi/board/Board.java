package me.anandjoshi.board;

/**
 * Created by anand on 04-12-2016.
 */

public class Board
{
    private Mark[][] board;
    private  final  int size;

    public Board(int size)
    {
        this.size = size;
        this.board = new Mark[size][size];
        clearBoard();
    }

    public int getSize()
    {
        return this.size;
    }


    public boolean setBoardMarkAt(int m, int n, Mark move)
    {
        if (board[m][n] == Mark.E)
        {
            board[m][n] = move;

            return true;
        }

        return false;
    }

    public Mark getWinningMark()
    {
        boolean x = isWinningFor(Mark.X);
        boolean o = isWinningFor(Mark.O);

        if(x)
            return Mark.X;
        else if (o)
            return Mark.O;

        return Mark.E;
    }

    private boolean isWinningFor(Mark mark)
    {
        return (diagonalCheck(mark)||antiDiagonalCheck(mark)||rowCheck(mark)||colCheck(mark));
    }

    public static void main(String[] args)
    {
        Board board = new Board(3);
        board.printBoard();

        board.setBoardMarkAt(0,2, Mark.X);
        board.setBoardMarkAt(1,1, Mark.X);
        board.setBoardMarkAt(2,2, Mark.X);

        System.out.println();
        board.printBoard();

        if (board.getWinningMark() == Mark.X)
            System.out.println("X won !");

       /* System.out.println(board.antiDiagonalCheck(Mark.X));
        System.out.println(board.diagonalCheck(Mark.X));
        System.out.println(board.rowCheck(Mark.X));
        System.out.println(board.colCheck(Mark.X));*/

    }


    public void printBoard()
    {
        for (int i = 0; i < size ; i++)
        {
            for (int j = 0; j < size ; j++)
            {
                System.out.print(board[i][j].toString()+"\t");
            }

            System.out.println();
        }
    }

    private boolean antiDiagonalCheck(Mark mark)
    {

        for (int i = 0; i < size ; i++)
        {
            for (int j = 0; j < size ; j++)
            {
                if (i + j == size - 1)
                {
                    if (!(board[i][j] == mark))
                        return  false;
                }
            }
        }

        return true;
    }


    private boolean diagonalCheck(Mark mark)
    {

        for (int i = 0; i < size ; i++)
        {
            for (int j = 0; j < size ; j++)
            {
                if ( i == j )
                {
                    if (!(board[i][j] == mark))
                        return  false;
                }
            }
        }

        return true;
    }


    private boolean rowCheck(Mark mark)
    {
        for (int i = 0; i < size ; i++)
        {
            boolean rowFlag = true;

            for (int j = 0; j < size ; j++)
            {
               if (!(board[i][j] == mark))
               {
                   rowFlag = false;
                   break;
               }
            }

            if (rowFlag)
                return true;

        }

        return false;
    }

    private  boolean colCheck(Mark mark)
    {
        for (int i = 0; i < size ; i++)
        {
            boolean rowFlag = true;

            for (int j = 0; j < size ; j++)
            {
                if (!(board[j][i] == mark))
                {
                    rowFlag = false;
                    break;
                }
            }

            if (rowFlag)
                return true;

        }

        return false;
    }

    public void clearBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length ; j++)
            {
              board[i][j] = Mark.E;
            }
        }
    }
}
