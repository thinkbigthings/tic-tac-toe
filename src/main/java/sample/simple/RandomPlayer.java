package sample.simple;


public class RandomPlayer implements Player {
    private Board.Play playSymbol;
    private int boardSize;

    @Override
    public Board.Play getPlaySymbol() {
        return playSymbol;
    }

    public RandomPlayer(Board.Play symbol, int boardDimension) {
        playSymbol = symbol;
        boardSize = boardDimension;
    }

    @Override
    public Board getNextMove(Board currentBoard) {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                try {
                    return currentBoard.withPlay(new Board.Slot(r, c), playSymbol);
                } catch (IllegalArgumentException ex) {
                    // keep trying
                }
            }
        }
        return currentBoard;
    }
}
