package sample.simple;

public interface Player {
    public Board getNextMove(Board currentBoard);

    public Board.Play getPlaySymbol();
}
