package me.anandjoshi.players;

import me.anandjoshi.board.Board;
import me.anandjoshi.board.Mark;

/**
 * Created by anand on 04-12-2016.
 */

public class NoPlayer extends Player
{
    private static final String NO_PLAYER = "No Player";

    public NoPlayer()
    {
        super(NO_PLAYER, null);
    }

    @Override
    public boolean move(Board board, Location nextMove) throws NoPlayerException
    {
        throw new NoPlayerException("There is no player to make a move !");
    }
}
