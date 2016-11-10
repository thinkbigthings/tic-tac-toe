package org.thinkbigthings.tictactoe;


import java.util.Optional;

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
    private int playCount = 0;
    private Optional<PlayerToken> winner = Optional.empty();
    private boolean moveAvailable = true;

    public boolean isMoveAvailable() {
        return moveAvailable;
    }

    public Optional<PlayerToken> getWinner() {
        return winner;
    }

    public Board(int size) {
        boardSize = size;
        positions = new PlayerToken[size][size];
    }

    public Board(Board toCopy) {
        boardSize = toCopy.boardSize;
        playCount = toCopy.playCount;
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
        return playCount == (boardSize * boardSize);
    }

    // TODO don't expose this if just for testing, to test, just get the winner
    public boolean isWinner(PlayerToken play) {
        return winner.isPresent() ? winner.get().equals(play) : false;
    }

    // starting with complexity 14
    private boolean isWinner(PlayerToken play, Slot position) {

        int playCountDiag = 0;
        int playCountAntiDiag = 0;
        int rowPlayCount = 0;
        int colPlayCount = 0;

        for (int i = 0; i < boardSize; i++) {

            // check diagonals
            if (play.equals(positions[i][i])) {
                playCountDiag++;
            }
            if (play.equals(positions[i][(boardSize - 1) - i])) {
                playCountAntiDiag++;
            }
            // check rows and columns
            if(play.equals(positions[i][position.col])) {
                colPlayCount++;
            }
            if(play.equals(positions[position.row][i])) {
                rowPlayCount++;
            }
        }

        if(playCountDiag == boardSize || playCountAntiDiag == boardSize || rowPlayCount == boardSize || colPlayCount == boardSize) {
            return true;
        }

        return false;
    }

    public Board withPlay(Slot position, PlayerToken player) {
        if(!moveAvailable) {
            throw new IllegalArgumentException("No moves are available");
        }
        if( positions[position.row][position.col] != null) {
            throw new IllegalArgumentException("Can't move here");
        }

        Board newBoard = new Board(this);
        newBoard.positions[position.row][position.col] = player;
        newBoard.playCount++;
        newBoard.winner = winner;

        if(newBoard.isWinner(player, position)) {
            newBoard.winner = Optional.of(player);
            newBoard.moveAvailable = false;
        }
        else if(isFull()) {
            newBoard.moveAvailable = false;
        }

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
