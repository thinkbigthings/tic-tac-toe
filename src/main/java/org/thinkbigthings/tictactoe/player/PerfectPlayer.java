package org.thinkbigthings.tictactoe.player;


import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

// TODO non-player methods should eventually be private
public class PerfectPlayer implements Player {

    private PlayerToken playSymbol;
    private PlayerToken opponent = new PlayerToken(UUID.randomUUID().toString());
    private int boardSize;

    @Override
    public PlayerToken getPlaySymbol() {
        return playSymbol;
    }

    public PerfectPlayer(PlayerToken symbol, int size) {
        playSymbol = symbol;
        boardSize = size;

        buildGameTree();
    }

    public void buildGameTree() {

        Board board = new Board(boardSize);

        Node<Board> tree = new Node<>(board, getAvailableMoves(board, playSymbol));
    }
    
    public void buildGameTree(Node<Board> parent) {
        
    }

    public Set<Board> getAvailableMoves(Board parent, PlayerToken nextPlayer) {
        return parent.getAvailableMoves()
                .stream()
                .map(c -> parent.withPlay(c, nextPlayer))
                .collect(Collectors.toSet());
    }

    @Override
    public Board getNextMove(Board currentBoard) {
        return currentBoard;
    }

    public static class Node<T> {
        private T node;
        private Set<T> children = new HashSet<>();
        public Node(T parent, Set<T> subnodes) {
            node = parent;
            children = subnodes;
        }
        public Set<T> getChildren() {
            return children;
        }
    }


}
