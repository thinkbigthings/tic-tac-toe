package org.thinkbigthings.tictactoe.player;


import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;

import java.util.Random;

public class RandomPlayer implements Player {

    private PlayerToken playSymbol;
    private final Random random = new Random();
    private int boardSize;

    @Override
    public PlayerToken getPlaySymbol() {
        return playSymbol;
    }

    public RandomPlayer(PlayerToken symbol, int size) {
        playSymbol = symbol;
        boardSize = size;
    }

    @Override
    public Board getNextMove(Board currentBoard) {

        // TODO should not use a while(true) loop
        while( true ) {
            try {
                int r = random.nextInt(boardSize);
                int c = random.nextInt(boardSize);
                return currentBoard.withPlay(new Board.Cell(r, c), playSymbol);
            } catch (IllegalArgumentException ex) {
                // keep trying
            }
        }
    }
}
