package org.thinkbigthings.tictactoe;


/**
 * Created by young1 on 10/26/16.
 */
public class Board {

    // TODO call this slot or cell?
    public static class Slot {
        private int row=0;
        private int col=0;
        public Slot(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static class PlayerToken {

        private String display;

        public PlayerToken(String s) {
            display = s;
        }

        @Override
        public String toString() {
            return display;
        }
    }

    private int boardSize;
    private PlayerToken[][] positions;

    // play count tracking
    // TODO maybe move to own class?
    private int[] sumPlaysPerRowP1;
    private int[] sumPlaysPerRowP2;
    private int[] sumPlaysPerColP1;
    private int[] sumPlaysPerColP2;
    private int sumPlaysDiagP1;
    private int sumPlaysDiagP2;
    private int sumPlaysAntiDiagP1;
    private int sumPlaysAntiDiagP2;

    public enum GameState {
        WIN_PLAYER_1, WIN_PLAYER_2, DRAW, IN_PROGRESS;
    }

    public GameState getGameState(PlayerToken p1, PlayerToken p2) {
        if(isWinner(p1)) {
            return GameState.WIN_PLAYER_1;
        }
        else if(isWinner(p2)) {
            return GameState.WIN_PLAYER_2;
        }
        else if(isFull()) {
            return GameState.DRAW;
        }
        return GameState.IN_PROGRESS;
    }

    public Board(int size) {
        boardSize = size;
        positions = new PlayerToken[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                positions[r][c] = null;
            }
        }
    }

    public Board(Board toCopy) {
        boardSize = toCopy.boardSize;
        positions = new PlayerToken[boardSize][boardSize];
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                positions[r][c] = toCopy.positions[r][c];
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
                if( positions[r][c] != null) {
                    numberPlays++;
                }
            }
        }
        return numberPlays == (boardSize * boardSize);
    }

    // TODO can check a winning play in O(1) if you track player counts by row/col as you make them
    // starting with complexity 14
    public boolean isWinner(PlayerToken play) {

        // check rows
        for (int r = 0; r < boardSize; r++) {
            int playCount = 0;
            for (int c = 0; c < boardSize; c++) {
                if(play.equals(positions[r][c])) {
                    playCount++;
                }
            }
            if(playCount == boardSize)
                return true;
        }

        // check cols
        for (int c = 0; c < boardSize; c++) {
            int playCount = 0;
            for (int r = 0; r < boardSize; r++) {
                if(play.equals(positions[r][c])) {
                    playCount++;
                }
            }
            if(playCount == boardSize)
                return true;
        }

        // check diagonals
        int playCountDiag = 0;
        int playCountAntiDiag = 0;
        for (int d = 0; d < boardSize; d++) {
            if (play.equals(positions[d][d])) {
                playCountDiag++;
            }
            if (play.equals(positions[d][(boardSize - 1) - d])) {
                playCountAntiDiag++;
            }
        }
        if(playCountDiag == boardSize || playCountAntiDiag == boardSize) {
            return true;
        }

        return false;
    }

    public Board withPlay(Slot position, PlayerToken player) {
        if( positions[position.row][position.col] != null) {
            throw new IllegalArgumentException("Can't move here!");
        }
        Board newBoard = new Board(this);
        newBoard.positions[position.row][position.col] = player;
        return newBoard;
    }

    public String toString() {
        StringBuffer string = new StringBuffer();
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                String token = positions[r][c] == null ? " " : positions[r][c].toString();
                string.append(token);
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
