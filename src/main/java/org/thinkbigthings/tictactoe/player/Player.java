package org.thinkbigthings.tictactoe.player;

import org.thinkbigthings.tictactoe.Board;

public interface Player {

    Board getNextMove(Board currentBoard);
    Board.PlayerToken getPlaySymbol();
}
