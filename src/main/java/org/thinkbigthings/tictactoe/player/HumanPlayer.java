package org.thinkbigthings.tictactoe.player;

import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;

import java.io.BufferedReader;

public class HumanPlayer implements Player {

    // System.console() and Scanner are final and can't be mocked, so using BufferedReader instead
    private BufferedReader input;
    private PlayerToken playSymbol;

    @Override
    public PlayerToken getPlaySymbol() {
        return playSymbol;
    }

    public HumanPlayer(PlayerToken symbol, BufferedReader reader) {
        input = reader;
        playSymbol = symbol;
    }

    @Override
    public Board getNextMove(Board currentBoard) {

        Board newBoard = null;
        String entry = null;
        while(newBoard == null) {

            try {

                entry = input.readLine().trim();

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
                newBoard = currentBoard.withPlay(new Board.Cell(row, col), playSymbol);
            }
            catch (Exception ex) {
                System.out.println("I didn't understand " + entry + ", commands are tl, tm, tr, ml, mm, mr, bl, bm, br");
                // keep trying
            }
        }
        return newBoard;
    }
}
