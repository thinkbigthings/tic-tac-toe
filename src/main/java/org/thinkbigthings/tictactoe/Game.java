package org.thinkbigthings.tictactoe;


import org.thinkbigthings.tictactoe.player.HumanPlayer;
import org.thinkbigthings.tictactoe.player.PerfectPlayer;
import org.thinkbigthings.tictactoe.player.Player;
import org.thinkbigthings.tictactoe.player.RandomPlayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class Game {

    private Player p1;
    private Player p2;
    int boardSize;

    public Game(GameConfig config) {
        p1 = createPlayer(new PlayerToken(config.getTokenPlayer1()), config.getIdentityPlayer1());
        p2 = createPlayer(new PlayerToken(config.getTokenPlayer2()), config.getIdentityPlayer2());
        boardSize = config.getBoardSize();
    }

    protected Player createPlayer(PlayerToken symbol, String identity) {
        if(identity.equals("human")) {
            return new HumanPlayer(symbol, new BufferedReader(new InputStreamReader(System.in)));
        }
        if(identity.equals("computer")) {
            return new RandomPlayer(symbol);
        }
        if(identity.equals("ai")) {
            return new PerfectPlayer(symbol);
        }
        throw new IllegalArgumentException("can't determine player identity from " + identity);
    }

    public Optional<PlayerToken> play() {

        Player currentPlayer = p1;
        Board currentBoard = new Board(boardSize);

        // for 0.4.0
        // TODO write logs to a file instead of standard out so CLI UI is better. Use lambdas / async logs
        // TODO use a tic tac toe image on startup

        // for 0.4.1
        // TODO break build on decreasing code coverage
        // https://github.com/gradle/gradle/issues/824
        // http://stackoverflow.com/questions/35540823/minimum-code-coverage-threshold-in-jacoco-gradle

        // TODO connect to mbeans to monitor cpu and boot endpoints, may be some properties to set too
        // Although we don't have any health endpoints yet
        // http://stackoverflow.com/questions/30069643/remote-monitoring-with-visualvm-and-jmx
        // http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-jmx
        // try setting own mbeans?
        // Logs: o.s.j.e.a.AnnotationMBeanExporter : Registering beans for JMX exposure on startup

        // for 0.5.0
        // TODO handle TODO's elsewhere in the code or move to this list
        // TODO experiment with hot reloading

        // for 1.0
        // TODO improve algorithm for the AI
        // account for number of ways to win besides just nearest win, account for blocking opponent imminent win
        // TODO keep track of score between multiple games


        // for 2.x

        // TODO write a clickable interface with a web page

        // TODO generalize to Gomoku (https://en.wikipedia.org/wiki/Tic-tac-toe)

        // TODO experiment with board sizes besides 3

        // TODO use websockets

        System.out.println(currentBoard);

        boolean inProgress = true;

        Optional<PlayerToken> winner = currentBoard.getWinner();
        while (inProgress) {

            System.out.print("Player " + currentPlayer.getPlaySymbol() + ": Enter your move: ");

            currentBoard = currentPlayer.getNextMove(currentBoard);
            currentPlayer = (currentPlayer == p1) ? p2 : p1;

            System.out.println();
            System.out.println(currentBoard);

            inProgress = currentBoard.isMoveAvailable();
        }

        winner = currentBoard.getWinner();
        if(winner.isPresent()) {
            System.out.println("PLAYER " + winner.get() + " WINS!!!");
        }
        else {
            System.out.println("ITS A DRAW");
        }

        System.out.println();

        return winner;
    }
}
