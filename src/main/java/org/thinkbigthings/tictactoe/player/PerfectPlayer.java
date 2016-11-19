package org.thinkbigthings.tictactoe.player;


import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// TODO non-player methods should eventually be private
public class PerfectPlayer implements Player {

    private PlayerToken playSymbol;
    private PlayerToken opponent = new PlayerToken(UUID.randomUUID().toString());

    public PerfectPlayer(PlayerToken symbol, int size) {
        playSymbol = symbol;
    }

    @Override
    public PlayerToken getPlaySymbol() {
        return playSymbol;
    }

    @Override
    public Board getNextMove(Board currentBoard) {
        return currentBoard;
    }


    public static class BoardTree {

        private PlayerToken player1;
        private PlayerToken player2;


        public BoardTree(PlayerToken p1, PlayerToken p2) {
            player1 = p1;
            player2 = p2;
        }

        public Board getBestBoardForPlayer(Board currentBoard, PlayerToken player) {


            // TODO fix nextPlayer() to work with this.

            // TODO maybe want to just get the next move/slot and return that instead of the board. Should we rework Player to do that too?
            // TODO I think for this use case we can just diff getAvailableMoves() from current and next boards

            Node<Board> gameTree = buildGameTree(new Node<>(currentBoard), player);

            long maxWins = 0;
            Board nextBoard = gameTree.getChildren().iterator().next().getContent();
            // TODO can we do this loop with a stream?
            for(Node<Board> child : gameTree.getChildren()) {
                if(getNumberWinningBoards(child, player) > maxWins) {
                    nextBoard = child.getContent();
                }
            }
            return nextBoard;
        }

        /**
         *
         * @param root should already have its content set
         * @param player player to use for all children of root
         * @return root that's populated.
         */
        public Node<Board> buildGameTree(Node<Board> root, PlayerToken player) {
            PlayerToken nextPlayer = nextPlayer(player);
            // TODO with all the references to root, maybe this should be moved into that class
            root.setChildren(root.asNodes(getAvailableMoves(root.getContent(), player)));
            root.getChildren().stream().forEach(c -> buildGameTree(c, nextPlayer));
            return root;
        }

        public PlayerToken nextPlayer(PlayerToken previousPlayer) {
            return previousPlayer.equals(player1) ? player2 : player1;
        }

        public long getNumberWinningBoards(Node<Board> node, PlayerToken player) {
            return node.stream()
                    .map(n -> n.getContent())
                    .filter(b -> player.equals(b.getWinner().orElse(null)))
                    .count();
        }

        public Set<Board> getAvailableMoves(Board parent, PlayerToken player) {
            return parent.getAvailableMoves()
                    .stream()
                    .map(c -> parent.withPlay(c, player))
                    .collect(Collectors.toSet());
        }

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
            return Collections.unmodifiableSet(children);
        }

        public void setChildren(Set<Node<T>> newChildren) {
            children.clear();
            children.addAll(newChildren);
        }

        public Set<Node<T>> asNodes(Set<T> contents) {
            return contents.stream().map(t -> new Node<T>(t)).collect(Collectors.toSet());
        }

        // TODO return all nodes in depth first traversal, starting with this


        /**
         * // Get all values in the tree:
         t.stream().map(Tree::getContent).collect(toList());

         // Get even values:
         t.flattened().map(Tree::getContent).filter(v -> v % 2 == 0).collect(toList());

         // Sum of even values:
         t.flattened().map(Tree::getContent).filter(v -> v % 2 == 0).reduce((a, b) -> a + b);

         // Does it contain 13?
         t.flattened().anyMatch(t -> t.getContent() == 13);

         * @return all nodes in the tree including this
         */
        public Stream<Node<T>> stream() {

            // TODO this is not lazy and  is called for each node in the tree every time.
            // It probably could be improved using a Supplier
            // https://docs.oracle.com/javase/8/docs/api/java/util/stream/StreamSupport.html
            // https://docs.oracle.com/javase/8/docs/api/java/util/Spliterators.AbstractSpliterator.html

            return Stream.concat(
                    Stream.of(this),
                    children.stream().flatMap(Node::stream));
        }
    }

}
