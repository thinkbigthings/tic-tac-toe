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
    public void testPlayHorizontal() throws Exception {

        Board board = new Board(3)
                .withPlay(new Board.Cell(0,0), X)
                .withPlay(new Board.Cell(1,1), O)
                .withPlay(new Board.Cell(0,1), X)
                .withPlay(new Board.Cell(1,0), O);

        Player p1 = new PerfectPlayer(X);

        board = p1.getNextMove(board);

        assertEquals(p1.getPlaySymbol(), board.getWinner().get());
    }

    @Test
    public void testPlayVertical() throws Exception {

        Board board = new Board(3)
                .withPlay(new Board.Cell(0,0), X)
                .withPlay(new Board.Cell(0,1), O)
                .withPlay(new Board.Cell(1,0), X)
                .withPlay(new Board.Cell(1,1), O);

        Player p1 = new PerfectPlayer(X);

        board = p1.getNextMove(board);

        assertEquals(p1.getPlaySymbol(), board.getWinner().get());
    }

}
