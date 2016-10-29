package org.thinkbigthings.tictactoe;


import org.thinkbigthings.tictactoe.player.HumanPlayer;
import org.thinkbigthings.tictactoe.player.Player;
import org.thinkbigthings.tictactoe.player.RandomPlayer;

public class Game {

    private Player p1;
    private Player p2;
    int boardSize;

    public Game(GameConfig config) {
        p1 = createPlayer(Board.Play.valueOf(config.getPlayer1Token()), config.getPlayer1Identity());
        p2 = createPlayer(Board.Play.valueOf(config.getPlayer2Token()), config.getPlayer2Identity());
        boardSize = config.getBoardSize();
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

        // TODO set artifact versions
        
        // TODO update usage in README

        // TODO break build on decreasing code coverage
        // http://stackoverflow.com/questions/35540823/minimum-code-coverage-threshold-in-jacoco-gradle

        // TODO be able to monitor with JMX

        // TODO implement -help to print usage

        // TODO get player symbol from configuration instead of hard coding to board

        // TODO write logs to a file instead of standard out so CLI UI is better.

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
