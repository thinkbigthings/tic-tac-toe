package sample.simple;

import java.io.InputStream;
import java.util.Scanner;

public class HumanPlayer implements Player {

    private Scanner scanner;
    private Board.Play playSymbol;

    @Override
    public Board.Play getPlaySymbol() {
        return playSymbol;
    }

    public HumanPlayer(Board.Play symbol, InputStream in) {
        scanner = new Scanner(in);
        scanner.useDelimiter(System.lineSeparator());
        playSymbol = symbol;
    }

    @Override
    public Board getNextMove(Board currentBoard) {

        Board newBoard = null;
        while(newBoard == null) {
            try {
                String entry = scanner.next();
                String[] moveIndex = entry.split(" ");
                int row = Integer.parseInt(moveIndex[0]);
                int col = Integer.parseInt(moveIndex[1]);
                newBoard = currentBoard.withPlay(new Board.Slot(row, col), playSymbol);
            }
            catch (Exception ex) {
                System.out.println(ex);
                // keep trying
            }
        }
        return newBoard;
    }
}
