package org.thinkbigthings.tictactoe.player;


import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;

import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player {

    private PlayerToken playSymbol;
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
        List<Board.Cell> moves = currentBoard.getAvailableMoves();

        if(moves.isEmpty()) {
            throw new IllegalArgumentException("Board has no available moves");
        }

        Board.Cell nextMove = moves.get(random.nextInt(moves.size()));
        return currentBoard.withPlay(nextMove, playSymbol);
    }
}
