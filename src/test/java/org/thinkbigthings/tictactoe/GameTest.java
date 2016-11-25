package org.thinkbigthings.tictactoe;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testPlayRandomGame() throws Exception {

        GameConfig config = new GameConfig();
        config.setIdentityPlayer1("computer");

        for(int i=0; i < 100; i++) {
            Game game = new Game(config);
            game.play();
        }

        // TODO this is basically an integration test that implicitly asserts that no exceptions are thrown during a normal game
        // should try to explicitly assert something about the code.
    }

    @Test
    public void testIntegrationAIShouldWin() throws Exception {

        // FIXME if ai always goes first, it should always win or draw

        GameConfig config = new GameConfig();
        config.setIdentityPlayer1("ai");
        config.setIdentityPlayer2("computer");

        // TODO test a lot more games, maybe 100 or more
        for(int i=0; i < 3; i++) {
            System.out.println("playing game " + i);
            Game game = new Game(config);
            Optional<PlayerToken> winner = game.play();
           // assertNotEquals(config.getTokenPlayer2(), winner.get().toString());
        }


    }

}
