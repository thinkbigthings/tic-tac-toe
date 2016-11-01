package org.thinkbigthings.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Board.PlayerToken X = new Board.PlayerToken("X");
    private Board.PlayerToken O = new Board.PlayerToken("O");

    @Test
    public void testPlayRandomGame() throws Exception {

        GameConfig config = new GameConfig();
        config.setIdentityPlayer1("computer");
        Game game = new Game(config);
        Board.GameState endState = game.play();

        assertNotEquals(Board.GameState.IN_PROGRESS, endState);
    }

}
