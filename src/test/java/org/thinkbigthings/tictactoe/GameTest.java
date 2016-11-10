package org.thinkbigthings.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testPlayRandomGame() throws Exception {

        GameConfig config = new GameConfig();
        config.setIdentityPlayer1("computer");
        Game game = new Game(config);
        game.play();

    }

}
