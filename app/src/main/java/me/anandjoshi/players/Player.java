package me.anandjoshi.players;

import me.anandjoshi.board.Board;
import me.anandjoshi.board.Mark;

/**
 * Created by anand on 04-12-2016.
 */

public abstract class Player
{
    private String name;
    private PlayerStatus state;
    private Mark mark;

    public Player(String name, Mark mark)
    {
        this.name = name;
        this.mark = mark;
        this.state = PlayerStatus.PLAYING;
    }

    public abstract boolean move(Board board, Location move) throws NoPlayerException;

    public String getName()
    {
        return name;
    }

    public Mark getMark()
    {
        return this.mark;
    }

    public PlayerStatus getState()
    {
        return this.state;
    }

    public void setState(PlayerStatus state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}


