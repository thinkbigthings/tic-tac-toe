package org.thinkbigthings.tictactoe;


/**
 * Created by young1 on 10/26/16.
 */
public class Board {

    // TODO call this slot or cell?
    public static class Slot {
        private int row=0;
        private int col=0;
        public Slot(int r, int c) {
            row = r;
            col = c;
        }
    }

    public enum Play {

        X("X"), O("O"), UNPLAYED(" ");

        private String display;

        Play(String s) {
            display = s;
        }

        @Override
        public String toString() {
            return display;
        }
    }

    private int boardSize = 3;
    private Play[][] positions = new Play[3][3];

    public Board(int size) {
        boardSize = size;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                positions[r][c] = Play.UNPLAYED;
            }
        }
    }

    public int size() {
        return boardSize;
    }

    public boolean isFull() {
        // TODO increment count of number of plays while playing so this is O(1)
        int numberPlays = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if(!positions[r][c].equals(Play.UNPLAYED)) {
                    numberPlays++;
                }
            }
        }
        return numberPlays == (boardSize * boardSize);
    }

    // TODO can check a winning play in O(1) if you track player counts by row/col as you make them
    public boolean isWinner(Play play) {

        // check rows
        for (int r = 0; r < boardSize; r++) {
            int playCount = 0;
            for (int c = 0; c < boardSize; c++) {
                if(positions[r][c].equals(play))
                    playCount++;
            }
            if(playCount == boardSize)
                return true;
        }

        // check cols
        for (int c = 0; c < boardSize; c++) {
            int playCount = 0;
            for (int r = 0; r < boardSize; r++) {
                if(positions[r][c].equals(play))
                    playCount++;
            }
            if(playCount == boardSize)
                return true;
        }

        // check diagonals if odd board size
        if(this.boardSize % 2 == 1) {
            int playCountDiag = 0;
            int playCountAntiDiag = 0;
            for (int d = 0; d < boardSize; d++) {
                if (positions[d][d].equals(play)) {
                    playCountDiag++;
                }
                if (positions[d][(boardSize - 1) - d].equals(play)) {
                    playCountAntiDiag++;
                }
            }
            if(playCountDiag == boardSize || playCountAntiDiag == boardSize)
                return true;
        }

        return false;
    }

    public Board withPlay(Slot position, Play player) {
        if(!positions[position.row][position.col].equals(Play.UNPLAYED)) {
            throw new IllegalArgumentException("Can't move here!");
        }
        Board newBoard = new Board(boardSize);
        newBoard.setPositions(this);
        newBoard.positions[position.row][position.col] = player;
        return newBoard;
    }

    public Board(Board previous, Slot position, Play player) {
        setPositions(previous);
        positions[position.row][position.col] = player;
    }

    // TODO is there a way to have a single method to iterate over cells that takes a lambda for assignments or tests?
    private void setPositions(Board oldBoard) {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                positions[r][c] = oldBoard.positions[r][c];
            }
        }
    }

    public String toString() {
        StringBuffer string = new StringBuffer();
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                string.append(positions[r][c]);
                if(c < boardSize-1 ) {
                    string.append("|");
                }
            }

            string.append(System.lineSeparator());
            if( r < boardSize-1) {
                string.append("------");
            }
            string.append(System.lineSeparator());
        }
        return string.toString();
    }

}
