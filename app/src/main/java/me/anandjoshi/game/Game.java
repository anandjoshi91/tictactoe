package me.anandjoshi.game;

import me.anandjoshi.players.Player;

/**
 * Created by anand on 04-12-2016.
 */
public interface Game
{
    void startGame();
    void endGame();
    Player getWinner();
}
