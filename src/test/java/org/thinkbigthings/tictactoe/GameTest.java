package org.thinkbigthings.tictactoe;

import org.junit.Test;

import java.util.Date;
import java.util.Optional;


public class GameTest {

    @Test(timeout=6000)
    public void testIntegrationAIShouldWin() throws Exception {

        // FIXME if ai always goes first, it should always win or draw

        GameConfig config = new GameConfig();
        config.setIdentityPlayer1("ai");
        config.setIdentityPlayer2("computer");

        long startTime = new Date().getTime();
        // TODO test a lot more games, maybe 100 or more in parallel
        for(int i=0; i < 3; i++) {
            System.out.println("playing game " + i);

            Game game = new Game(config);
            Optional<PlayerToken> winner = game.play();
           // assertNotEquals(config.getTokenPlayer2(), winner.get().toString());
        }
        long endTime = new Date().getTime();
        System.out.println("games took " + (endTime-startTime));


    }

}
