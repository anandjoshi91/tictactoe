package me.anandjoshi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.anandjoshi.board.Board;
import me.anandjoshi.board.Mark;
import me.anandjoshi.game.Game;
import me.anandjoshi.game.GameState;
import me.anandjoshi.players.HumanPlayer;
import me.anandjoshi.players.Location;
import me.anandjoshi.players.NoPlayer;
import me.anandjoshi.players.NoPlayerException;
import me.anandjoshi.players.Player;
import me.anandjoshi.players.PlayerStatus;

public class AppStart extends AppCompatActivity implements View.OnClickListener, Game
{
    private static int moveCount = 0;
    private Button[] buttons = new Button[9];
    Board board;
    Player playerOne;
    Player playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);
        initButtons();
        startGame();

    }


    @Override
    public void onClick(View v)
    {
        Button clickedButton = (Button) findViewById(v.getId());
        clickedButton.setTextSize(40);
        Location nextMove = getPlayerLocation(v.getId());
        boolean validMove = false;
        Player player;

        try
        {
            player = getCurrentPlayer();

            validMove = playerMove(player,nextMove);

            if (validMove)
            {
                clickedButton.setText(player.getMark().toString());
                moveCount++;
                updateGameStatus();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private Player getCurrentPlayer()
    {
        Player player;
        if (moveCount % 2 == 0)
        {
            player = playerOne;
        }
        else
        {
            player = playerTwo;
        }
        return player;
    }

    private boolean playerMove(Player player, Location nextMove) throws NoPlayerException
    {
        boolean validMove = false;
        validMove = player.move(board, nextMove);
        return validMove;
    }


    public Location getPlayerLocation(int id)
    {
        switch (id)
        {
            case R.id.cell1:
                return (new Location(0,0));

            case R.id.cell2:
                return(new Location(0,1));

            case R.id.cell3:
                return (new Location(0,2));

            case R.id.cell4:
                return (new Location(1,0));

            case R.id.cell5:
                return (new Location(1,1));

            case R.id.cell6:
                return (new Location(1,2));

            case R.id.cell7:
                return (new Location(2,0));

            case R.id.cell8:
                return (new Location(2,1));

            case R.id.cell9:
                return (new Location(2,2));
        }

        return null;
    }


    private void initButtons()
    {
        Button one = (Button) findViewById(R.id.cell1);
        one.setOnClickListener(this);
        buttons[0] = one;

        Button two = (Button) findViewById(R.id.cell2);
        two.setOnClickListener(this);
        buttons[1] = two;

        Button three = (Button) findViewById(R.id.cell3);
        three.setOnClickListener(this);
        buttons[2] = three;

        Button four = (Button) findViewById(R.id.cell4);
        four.setOnClickListener(this);
        buttons[3] = four;

        Button five = (Button) findViewById(R.id.cell5);
        five.setOnClickListener(this);
        buttons[4] = five;

        Button six = (Button) findViewById(R.id.cell6);
        six.setOnClickListener(this);
        buttons[5] = six;

        Button seven = (Button) findViewById(R.id.cell7);
        seven.setOnClickListener(this);
        buttons[6] = seven;

        Button eight = (Button) findViewById(R.id.cell8);
        eight.setOnClickListener(this);
        buttons[7] = eight;

        Button nine = (Button) findViewById(R.id.cell9);
        nine.setOnClickListener(this);
        buttons[8] = nine;
    }

    @Override
    public void startGame()
    {
        board = new Board(3);
        playerOne = new HumanPlayer("Player One",Mark.X);
        playerTwo = new HumanPlayer("Player Two",Mark.O);
    }

    @Override
    public void endGame()
    {
        Toast.makeText(getApplicationContext(), "Winner : "+getWinner(), Toast.LENGTH_LONG).show();
        moveCount = 0;
        clearButtons();
        board.clearBoard();
        playerOne.setState(PlayerStatus.PLAYING);
        playerTwo.setState(PlayerStatus.PLAYING);
    }

    @Override
    public Player getWinner()
    {
        if (playerOne.getState() == PlayerStatus.PLAYING && playerTwo.getState() == PlayerStatus.PLAYING)
            return new NoPlayer();
        else if (playerOne.getState() == PlayerStatus.DRAW || playerTwo.getState() == PlayerStatus.DRAW)
            return new NoPlayer();
        else if (playerOne.getState() == PlayerStatus.WON || playerTwo.getState() == PlayerStatus.LOST)
            return playerOne;
        else
            return playerTwo;
    }

    private void updateGameStatus()
    {
        Mark mark = board.getWinningMark();

        if(mark == Mark.X)
        {
            playerOne.setState(PlayerStatus.WON);
            playerTwo.setState(PlayerStatus.LOST);
            endGame();
        }
        else if (mark == Mark.O)
        {
            playerOne.setState(PlayerStatus.LOST);
            playerTwo.setState(PlayerStatus.WON);
            endGame();
        }

        if (moveCount == board.getSize()*board.getSize() - 1)
        {
            playerOne.setState(PlayerStatus.DRAW);
            playerTwo.setState(PlayerStatus.DRAW);
            endGame();
        }

    }


    private void clearButtons()
    {
        for (int i = 0; i < buttons.length ; i++)
        {
            buttons[i].setText("");
        }
    }

}

