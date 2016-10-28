package org.thinkbigthings.tictactoe;


import org.thinkbigthings.tictactoe.player.HumanPlayer;
import org.thinkbigthings.tictactoe.player.Player;
import org.thinkbigthings.tictactoe.player.RandomPlayer;

public class Game {

    private Player p1;
    private Player p2;
    int boardSize;

    public Game(int boardSize, String playerIdentity1,Board.Play player1, String playerIdentity2, Board.Play player2) {
        p1 = createPlayer(player1, playerIdentity1);
        p2 = createPlayer(player2, playerIdentity2);
        this.boardSize = boardSize;
    }

    protected Player createPlayer(Board.Play symbol, String identity) {
        if(identity.equals("human")) {
            return new HumanPlayer(symbol, System.in);
        }
        if(identity.equals("computer")) {
            return new RandomPlayer(symbol);
        }
        throw new IllegalArgumentException("can't determine player identity from " + identity);
    }

    public void play() {

        Player currentPlayer = p1;
        Board currentBoard = new Board(boardSize);

        // TODO write unit tests

        // TODO put board dimensions in properties file and command line

        // TODO specify player letters X and O in properties or readme

        // TODO specify human or AI as player 1 or player 2 in properties or command line

        // TODO update usage in README

        // TODO write logs to a file instead of standard out so UI is better.

        // TODO use a tic tac toe image on startup

        // TODO keep track of score between multiple games

        // TODO write a winning algorithm for the AI

        // TODO write a clickable interface with a web page

        // TODO use websockets

        System.out.println(currentBoard);

        boolean gameInProgress = true;

        while (gameInProgress) {

            System.out.print("Player " + currentPlayer.getPlaySymbol() + ": Enter your move: ");

            currentBoard = currentPlayer.getNextMove(currentBoard);

            System.out.println();
            System.out.println(currentBoard);

            if (currentBoard.isWinner(currentPlayer.getPlaySymbol())) {
                System.out.println("PLAYER " + currentPlayer.getPlaySymbol() + " WINS!!!");
                gameInProgress = false;
            } else if (currentBoard.isFull()) {
                System.out.println("ITS A DRAW");
                gameInProgress = false;
            }

            currentPlayer = (currentPlayer == p1) ? p2 : p1;
        }

        System.out.println();
    }
}
