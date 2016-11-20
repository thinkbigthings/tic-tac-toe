package org.thinkbigthings.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testPlayRandomGame() throws Exception {

        GameConfig config = new GameConfig();
        config.setIdentityPlayer1("computer");

        // FIXME this is broken because it occasionally breaks at the end of a game.

        for(int i=0; i < 100; i++) {
            System.out.println("-------- GAME " + i);
            Game game = new Game(config);
            game.play();
        }

        // TODO this is basically an integration test that implicitly asserts that no exceptions are thrown during a normal game
        // should try to explicitly assert something about the code.
    }

}
