package org.thinkbigthings.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void testFull() throws Exception {

        Board empty = new Board(3);
        assertFalse(empty.isFull());

        Board full = new Board(1).withPlay(new Board.Slot(0,0), Board.Play.X);
        assertTrue(full.isFull());
    }

    @Test
    public void testToString() throws Exception {

        Board board = new Board(1);
        assertNotNull(board.toString());
    }
}
