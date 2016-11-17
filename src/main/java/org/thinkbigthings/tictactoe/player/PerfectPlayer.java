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

    public Node<Board> buildGameTree() {

        // TODO be able to build a tree
        // TODO be able to build a tree with players taking turns

        return buildGameTree(new Node<>(new Board(boardSize)));

    }

    public Node<Board> buildGameTree(Node<Board> parent) {
        parent.setChildren(parent.asNodes(getAvailableMoves(parent.getContent(), playSymbol)));
        parent.getChildren().stream().forEach(c -> buildGameTree(c));
        return parent;
    }

    public Set<Board> getAvailableMoves(Board parent, PlayerToken nextPlayer) {

        // FIXME getting an error that no moves are available
        // but if move is not available, stream is empty, right?

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

        private T content;
        private Set<Node<T>> children = new HashSet<>();

        public Node(T nodeContent) {
            content = nodeContent;
        }

        public T getContent() {
            return content;
        }

        public Set<Node<T>> getChildren() {
            return children;
        }

        public void setChildren(Set<Node<T>> newChildren) {
            children.clear();
            children.addAll(newChildren);
        }

        public Set<Node<T>> asNodes(Set<T> contents) {
            return contents.stream().map(t -> new Node<T>(t)).collect(Collectors.toSet());
        }
    }


}
