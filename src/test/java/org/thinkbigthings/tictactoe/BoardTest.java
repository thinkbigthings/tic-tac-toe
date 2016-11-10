package org.thinkbigthings.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private Board.PlayerToken X = new Board.PlayerToken("X");
    private Board.PlayerToken O = new Board.PlayerToken("O");

    @Test
    public void testFull() throws Exception {

        Board empty = new Board(3);
        assertFalse(empty.isFull());

        Board full = new Board(1).withPlay(new Board.Slot(0,0), X);
        assertTrue(full.isFull());
    }

    @Test
    public void testToString() throws Exception {

        Board board = new Board(1);
        assertNotNull(board.toString());
    }

    @Test
    public void testIsLoserDiagonal() throws Exception {
        assertFalse(createWinningDiag().isWinner(O));
    }

    @Test
    public void testIsWinnerDiagonal() throws Exception {
        assertTrue(createWinningDiag().isWinner(X));
    }

    @Test
    public void testIsWinnerAntiDiagonal() throws Exception {
        assertTrue(createWinningAntiDiag().isWinner(X));
    }

    @Test
    public void testIsWinnerRow() throws Exception {
        assertTrue(createWinningRow().isWinner(X));
    }

    @Test
    public void testIsWinnerColumn() throws Exception {
        assertTrue(createWinningCol().isWinner(X));
    }

    private Board createWinningRow() throws Exception {
        return new Board(2)
                .withPlay(new Board.Slot(0,0), X)
                .withPlay(new Board.Slot(0,1), X);
    }

    private Board createWinningCol() throws Exception {
        return new Board(2)
                .withPlay(new Board.Slot(0,0), X)
                .withPlay(new Board.Slot(1,0), X);
    }

    private Board createWinningDiag() throws Exception {
        return new Board(2)
                .withPlay(new Board.Slot(0,0), X)
                .withPlay(new Board.Slot(1,1), X);
    }

    private Board createWinningAntiDiag() throws Exception {
        return new Board(2)
                .withPlay(new Board.Slot(0,1), X)
                .withPlay(new Board.Slot(1,0), X);
    }
}
