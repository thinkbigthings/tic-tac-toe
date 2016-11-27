package org.thinkbigthings.tictactoe.player;

import org.junit.Test;
import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;
import org.thinkbigthings.tictactoe.player.HumanPlayer;
import org.thinkbigthings.tictactoe.player.Player;


import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HumanPlayerTest {

    private final PlayerToken X = new PlayerToken("X");

    @Test
    public void testPlay() throws Exception {

        BufferedReader in = mock(BufferedReader.class);
        when(in.readLine()).thenReturn("tl");

        Player player = new HumanPlayer(X, in);
        Board currentBoard = player.getNextMove(new Board(1));
        assertEquals(X, currentBoard.getWinner().get());
    }

}
