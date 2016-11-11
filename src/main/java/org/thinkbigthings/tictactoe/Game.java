package org.thinkbigthings.tictactoe;


import org.thinkbigthings.tictactoe.player.HumanPlayer;
import org.thinkbigthings.tictactoe.player.Player;
import org.thinkbigthings.tictactoe.player.RandomPlayer;

import java.util.Optional;

public class Game {

    private Player p1;
    private Player p2;
    int boardSize;

    public Game(GameConfig config) {
        p1 = createPlayer(new Board.PlayerToken(config.getTokenPlayer1()), config.getIdentityPlayer1(), config);
        p2 = createPlayer(new Board.PlayerToken(config.getTokenPlayer2()), config.getIdentityPlayer2(), config);
        boardSize = config.getBoardSize();
    }

    protected Player createPlayer(Board.PlayerToken symbol, String identity, GameConfig config) {
        if(identity.equals("human")) {
            return new HumanPlayer(symbol, System.in);
        }
        if(identity.equals("computer")) {
            return new RandomPlayer(symbol, config.getBoardSize());
        }
        throw new IllegalArgumentException("can't determine player identity from " + identity);
    }

    public void play() {

        Player currentPlayer = p1;
        Board currentBoard = new Board(boardSize);

        // for 0.3.2
        // TODO be able to run with gradlew bootRun (can't take input from standard in, though, maybe try java.io.Console?)
        // http://stackoverflow.com/questions/13172137/console-application-with-java-and-gradle
        // TODO break build on decreasing code coverage
        // http://stackoverflow.com/questions/35540823/minimum-code-coverage-threshold-in-jacoco-gradle
        // TODO be able to monitor and record with JMX and jvisualvm

        // for 0.4.0
        // TODO write logs to a file instead of standard out so CLI UI is better. Use lambdas / async logs
        // TODO use a tic tac toe image on startup

        // for 0.5.0
        // TODO handle TODO's elsewhere in the code or move to this list
        // TODO experiment with hot reloading

        // for 1.0
        // TODO experiment with board sizes besides 3, command line entry doesn't work with letters, for example

        // for 1.x

        // TODO keep track of score between multiple games

        // TODO write a winning algorithm for the AI, optionally display chances of each player to win

        // TODO generalize to Gomoku (https://en.wikipedia.org/wiki/Tic-tac-toe)

        // for 2.x

        // TODO write a clickable interface with a web page

        // TODO use websockets

        System.out.println(currentBoard);

        boolean inProgress = true;

        while (inProgress) {

            System.out.print("Player " + currentPlayer.getPlaySymbol() + ": Enter your move: ");

            currentBoard = currentPlayer.getNextMove(currentBoard);
            currentPlayer = (currentPlayer == p1) ? p2 : p1;

            System.out.println();
            System.out.println(currentBoard);

            inProgress = currentBoard.isMoveAvailable();
        }

        Optional<Board.PlayerToken> winner = currentBoard.getWinner();
        if(winner.isPresent()) {
            System.out.println("PLAYER " + winner.get() + " WINS!!!");
        }
        else {
            System.out.println("ITS A DRAW");
        }

        System.out.println();
    }
}
