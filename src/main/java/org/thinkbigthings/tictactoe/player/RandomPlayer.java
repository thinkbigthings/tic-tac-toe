package org.thinkbigthings.tictactoe.player;


import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.Cell;
import org.thinkbigthings.tictactoe.PlayerToken;

import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player {

    private final PlayerToken playSymbol;
    private final Random random = new Random();

    @Override
    public PlayerToken getPlaySymbol() {
        return playSymbol;
    }

    public RandomPlayer(PlayerToken symbol) {
        playSymbol = symbol;
    }

    @Override
    public Board getNextMove(Board currentBoard) {
        List<Cell> moves = currentBoard.getAvailableMoves();

        if(moves.isEmpty()) {
            throw new IllegalArgumentException("Board has no available moves");
        }

        Cell nextMove = moves.get(random.nextInt(moves.size()));
        return currentBoard.withPlay(nextMove, playSymbol);
    }
}
