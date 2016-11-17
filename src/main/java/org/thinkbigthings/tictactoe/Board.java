package org.thinkbigthings.tictactoe;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {

    public static class Cell {
        private int row=0;
        private int col=0;
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private int boardSize;
    private PlayerToken[][] positions;
    private int playCount = 0;
    private Optional<PlayerToken> winner = Optional.empty();
    private boolean moveAvailable = true;

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

    public List<Cell> getAvailableMoves() {
        List<Cell> moves = new ArrayList<>();
        if(moveAvailable) {
            for (int r = 0; r < boardSize; r++) {
                for (int c = 0; c < boardSize; c++) {
                    if(positions[r][c] == null) {
                        moves.add(new Cell(r,c));
                    }
                }
            }
        }
        return moves;
    }

    public boolean isMoveAvailable() {
        return moveAvailable;
    }

    public Optional<PlayerToken> getWinner() {
        return winner;
    }

    private boolean isFull() {
        return playCount == (boardSize * boardSize);
    }

    private boolean isWinner(PlayerToken play, Cell position) {

        boolean allRow = true;
        boolean allCol = true;
        boolean allDiag = true;
        boolean allAntiDiag = true;

        for (int i = 0; i < boardSize; i++) {
            allDiag &= play.equals(positions[i][i]);
            allAntiDiag &= play.equals(positions[i][(boardSize - 1) - i]);
            allCol &= play.equals(positions[i][position.col]);
            allRow &= play.equals(positions[position.row][i]);
        }

        return allRow || allCol || allDiag || allAntiDiag;
    }

    public Board withPlay(Cell position, PlayerToken player) {
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
