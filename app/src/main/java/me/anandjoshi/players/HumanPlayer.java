package me.anandjoshi.players;

import java.util.Scanner;

import me.anandjoshi.activities.AppStart;
import me.anandjoshi.board.Board;
import me.anandjoshi.board.Mark;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by anand on 04-12-2016.
 */

public class HumanPlayer extends Player
{

    public HumanPlayer(String name, Mark mark)
    {
        super(name, mark);
    }

    @Override
    public boolean move( Board board, Location move)
    {
          return board.setBoardMarkAt(move.getX(), move.getY(), this.getMark());
    }



}
