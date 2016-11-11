package org.thinkbigthings.tictactoe;

public class PlayerToken {

    private String display;

    public PlayerToken(String s) {
        display = s;
    }

    @Override
    public String toString() {
        return display;
    }
}
