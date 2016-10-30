package org.thinkbigthings.tictactoe.player;


import org.thinkbigthings.tictactoe.Board;

import java.util.Random;

public class RandomPlayer implements Player {

    private Board.Play playSymbol;
    private final Random random = new Random();

    @Override
    public Board.Play getPlaySymbol() {
        return playSymbol;
    }

    public RandomPlayer(Board.Play symbol) {
        playSymbol = symbol;
    }

    @Override
    public String getPlaySymbolString() {
        return playSymbol.toString();
    }

    @Override
    public Board getNextMove(Board currentBoard) {

        // TODO should not use a while(true) loop
        while( true ) {
            try {
                int r = random.nextInt(currentBoard.size());
                int c = random.nextInt(currentBoard.size());
                return currentBoard.withPlay(new Board.Slot(r, c), playSymbol);
            } catch (IllegalArgumentException ex) {
                // keep trying
            }
        }
    }
}
