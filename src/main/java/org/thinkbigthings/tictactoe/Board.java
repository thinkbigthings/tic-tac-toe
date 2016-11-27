package org.thinkbigthings.tictactoe;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {

    private final int boardSize;
    private final PlayerToken[][] positions;
    private int playCount = 0;
    private PlayerToken winner = null;
    private boolean moveAvailable = true;

    public Board(int size) {
        boardSize = size;
        positions = new PlayerToken[size][size];
    }

    public Board(Board toCopy) {
        boardSize = toCopy.boardSize;
        playCount = toCopy.playCount;
        winner = toCopy.winner;
        positions = new PlayerToken[boardSize][boardSize];
        for (int r = 0; r < boardSize; r++) {
            System.arraycopy(toCopy.positions[r], 0, positions[r], 0, boardSize);
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

    public int getAvailableMoveCount() {
        return (boardSize*boardSize) - playCount;
    }

    public boolean isWinner(PlayerToken player) {
        return player.equals(winner);
    }

    public Optional<PlayerToken> getWinner() {
        return Optional.ofNullable(winner);
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
            allCol &= play.equals(positions[i][position.getColumn()]);
            allRow &= play.equals(positions[position.getRow()][i]);
        }

        return allRow || allCol || allDiag || allAntiDiag;
    }

    public Board withPlay(Cell position, PlayerToken player) {
        if(!moveAvailable) {
            throw new IllegalArgumentException("No moves are available");
        }
        if( positions[position.getRow()][position.getColumn()] != null) {
            throw new IllegalArgumentException("Can't move here");
        }

        Board newBoard = new Board(this);
        newBoard.positions[position.getRow()][position.getColumn()] = player;
        newBoard.playCount++;

        if(newBoard.isWinner(player, position)) {
            newBoard.winner = player;
            newBoard.moveAvailable = false;
        }
        else if(newBoard.isFull()) {
            newBoard.moveAvailable = false;
        }

        return newBoard;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
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
