package org.thinkbigthings.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private final PlayerToken X = new PlayerToken("X");
    private final PlayerToken O = new PlayerToken("O");

    @Test
    public void testNoMovesAfterWin() {
        assertFalse(createWinningRow().isMoveAvailable());
        assertTrue(createWinningRow().getAvailableMoves().isEmpty());
    }

    @Test
    public void testToString() {
        Board board = new Board(1);
        assertNotNull(board.toString());
    }

    @Test
    public void testIsLoserDiagonal() {
        assertFalse(createWinningDiag().getWinner().get().equals(O));
    }

    @Test
    public void testIsWinnerDiagonal() {
        assertTrue(createWinningDiag().getWinner().get().equals(X));
    }

    @Test
    public void testIsWinnerAntiDiagonal()  {
        assertTrue(createWinningAntiDiag().getWinner().get().equals(X));
    }

    @Test
    public void testIsWinnerRow() {
        assertTrue(createWinningRow().getWinner().get().equals(X));
    }

    @Test
    public void testIsWinnerColumn() {
        assertTrue(createWinningCol().getWinner().get().equals(X));
    }

    private Board createWinningRow() {
        return new Board(2)
                .withPlay(new Cell(0,0), X)
                .withPlay(new Cell(0,1), X);
    }

    private Board createWinningCol() {
        return new Board(2)
                .withPlay(new Cell(0,0), X)
                .withPlay(new Cell(1,0), X);
    }

    private Board createWinningDiag() {
        return new Board(2)
                .withPlay(new Cell(0,0), X)
                .withPlay(new Cell(1,1), X);
    }

    private Board createWinningAntiDiag() {
        return new Board(2)
                .withPlay(new Cell(0,1), X)
                .withPlay(new Cell(1,0), X);
    }
}
