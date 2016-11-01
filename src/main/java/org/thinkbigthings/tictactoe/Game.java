package org.thinkbigthings.tictactoe;


import org.thinkbigthings.tictactoe.player.HumanPlayer;
import org.thinkbigthings.tictactoe.player.Player;
import org.thinkbigthings.tictactoe.player.RandomPlayer;

public class Game {

    private Player p1;
    private Player p2;
    int boardSize;

    public Game(GameConfig config) {
        p1 = createPlayer(new Board.PlayerToken(config.getPlayer1Token()), config.getPlayer1Identity());
        p2 = createPlayer(new Board.PlayerToken(config.getPlayer2Token()), config.getPlayer2Identity());
        boardSize = config.getBoardSize();
    }

    protected Player createPlayer(Board.PlayerToken symbol, String identity) {
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

        // for 0.3.1

        // TODO use constructor injection for game config properties
        // TODO unit test the Game with random players
        // TODO write a ScoreKeeper to use behind the scenes of Board's gameState

        // for 0.3.2
        // TODO reduce complexity, start with Board.isWinner()
        // TODO remap jacoco task name to just "coverage"
        // TODO be able to run with gradlew bootRun (can't take input from standard in, though, maybe try java.io.Console?)
        // http://stackoverflow.com/questions/13172137/console-application-with-java-and-gradle
        // TODO break build on decreasing code coverage
        // http://stackoverflow.com/questions/35540823/minimum-code-coverage-threshold-in-jacoco-gradle
        // TODO be able to monitor and record with JMX and jvisualvm

        // for 0.4.0
        // TODO write logs to a file instead of standard out so CLI UI is better.
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

        Board.GameState state = currentBoard.getGameState(p1.getPlaySymbol(), p2.getPlaySymbol());

        while (state.equals(Board.GameState.IN_PROGRESS)) {

            System.out.print("Player " + currentPlayer.getPlaySymbol() + ": Enter your move: ");

            currentBoard = currentPlayer.getNextMove(currentBoard);
            currentPlayer = (currentPlayer == p1) ? p2 : p1;

            System.out.println();
            System.out.println(currentBoard);

            state = currentBoard.getGameState(p1.getPlaySymbol(), p2.getPlaySymbol());
            switch(state) {
                case WIN_PLAYER_1:
                    System.out.println("PLAYER " + p1.getPlaySymbol() + " WINS!!!");
                    break;
                case WIN_PLAYER_2:
                    System.out.println("PLAYER " + p2.getPlaySymbol() + " WINS!!!");
                    break;
                case DRAW:
                    System.out.println("ITS A DRAW");
                    break;
                case IN_PROGRESS:
                    break;
            }

        }

        System.out.println();
    }
}
