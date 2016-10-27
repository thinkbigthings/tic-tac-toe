package sample.simple.player;

import sample.simple.Board;

public interface Player {

    Board getNextMove(Board currentBoard);
    Board.Play getPlaySymbol();
}
