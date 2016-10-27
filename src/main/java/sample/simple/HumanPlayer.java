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
        String entry = null;
        while(newBoard == null) {

            try {

                entry = scanner.next().trim();

                int row = 0;
                int col = 0;
                String r = entry.substring(0,1);
                String c = entry.substring(1,2);
                if(r.equals("t")) {
                    row = 0;
                }
                else if(r.equals("m")) {
                    row = 1;
                }
                else if(r.equals("b")) {
                    row = 2;
                }
                else {
                    throw new IllegalArgumentException();
                }
                if(c.equals("l")) {
                    col = 0;
                }
                else if(c.equals("m")) {
                    col = 1;
                }
                else if(c.equals("r")) {
                    col = 2;
                }
                else {
                    throw new IllegalArgumentException();
                }
                newBoard = currentBoard.withPlay(new Board.Slot(row, col), playSymbol);
            }
            catch (Exception ex) {
                System.out.println("I didn't understand " + entry + ", commands are tl, tm, tr, ml, mm, mr, bl, bm, br");
                // keep trying
            }
        }
        return newBoard;
    }
}
