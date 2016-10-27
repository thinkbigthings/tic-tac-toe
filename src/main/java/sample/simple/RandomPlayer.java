package sample.simple;


import java.util.Random;

public class RandomPlayer implements Player {

    private Board.Play playSymbol;
    private int boardSize;
    private final Random random = new Random();

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

        // TODO should not use a while(true) loop
        while( true ) {
            try {
                int r = random.nextInt(3);
                int c = random.nextInt(3);
                return currentBoard.withPlay(new Board.Slot(r, c), playSymbol);
            } catch (IllegalArgumentException ex) {
                // keep trying
            }
        }
    }
}
