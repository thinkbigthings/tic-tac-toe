package org.thinkbigthings.tictactoe.player;

import org.junit.Test;
import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PerfectPlayerTest {

    private PlayerToken X = new PlayerToken("X");
    private PlayerToken O = new PlayerToken("O");

    @Test
    public void testPlay() throws Exception {

        Board board = new Board(3);

        Player p1 = new PerfectPlayer(X);
        Player p2 = new RandomPlayer(O);

        board = p1.getNextMove(board);
        board = p2.getNextMove(board);
        board = p1.getNextMove(board);
        board = p2.getNextMove(board);
        board = p1.getNextMove(board);

        // FIXME this fails because perfect player isn't perfect
        // it tries to find game path with most potential winning moves instead of closest winning move

System.out.println(board);
        assertTrue(board.getWinner().get().equals(p1));
    }

}
