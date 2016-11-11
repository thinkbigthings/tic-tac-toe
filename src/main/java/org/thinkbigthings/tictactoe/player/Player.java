package org.thinkbigthings.tictactoe.player;

import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;

public interface Player {

    Board getNextMove(Board currentBoard);
    PlayerToken getPlaySymbol();
}
