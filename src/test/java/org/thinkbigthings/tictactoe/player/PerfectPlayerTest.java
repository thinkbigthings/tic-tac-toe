package org.thinkbigthings.tictactoe.player;

import org.junit.Test;
import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.Cell;
import org.thinkbigthings.tictactoe.PlayerToken;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PerfectPlayerTest {

    private final PlayerToken X = new PlayerToken("X");
    private final PlayerToken O = new PlayerToken("O");
    private final Player p1 = new PerfectPlayer(X);

    @Test
    public void testLogicalMove1() throws Exception {

        Board board = new Board(3)
                .withPlay(new Cell(0,2), X)
                .withPlay(new Cell(2,2), O);

        board = p1.getNextMove(board);

        // this is the worst move you could make, should not be next move for an AI
        assertFalse(board.getAvailableMoves().contains(new Cell(1,2)));
    }



    @Test
    public void testPlayHorizontalToWin() throws Exception {

        Board board = new Board(3)
                .withPlay(new Cell(0,0), X)
                .withPlay(new Cell(1,1), O)
                .withPlay(new Cell(0,1), X)
                .withPlay(new Cell(1,0), O);

        board = p1.getNextMove(board);

        assertEquals(p1.getPlaySymbol(), board.getWinner().get());
    }

    @Test
    public void testPlayVerticalToWin() throws Exception {

        Board board = new Board(3)
                .withPlay(new Cell(0,0), X)
                .withPlay(new Cell(0,1), O)
                .withPlay(new Cell(1,0), X)
                .withPlay(new Cell(1,1), O);

        board = p1.getNextMove(board);

        assertEquals(p1.getPlaySymbol(), board.getWinner().get());
    }

}
