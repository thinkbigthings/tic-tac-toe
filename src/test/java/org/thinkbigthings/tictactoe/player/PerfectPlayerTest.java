package org.thinkbigthings.tictactoe.player;

import org.junit.Test;
import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PerfectPlayerTest {

    private PlayerToken X = new PlayerToken("X");

    @Test
    public void testPlay() throws Exception {


        PerfectPlayer player = new PerfectPlayer(X, 3);
        PerfectPlayer.Node<Board> gameTree = player.buildGameTree();
        gameTree.getChildren();
    }

}
