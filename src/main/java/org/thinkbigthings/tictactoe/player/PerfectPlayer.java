package org.thinkbigthings.tictactoe.player;


import org.thinkbigthings.tictactoe.Board;
import org.thinkbigthings.tictactoe.PlayerToken;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PerfectPlayer implements Player {



    private final PlayerToken playSymbol;
    private final PlayerToken opponent = new PlayerToken(UUID.randomUUID().toString());

    public PerfectPlayer(PlayerToken symbol) {
        playSymbol = symbol;
    }

    @Override
    public PlayerToken getPlaySymbol() {
        return playSymbol;
    }

    @Override
    public Board getNextMove(Board currentBoard) {
        BoardTree tree = new BoardTree(playSymbol, opponent);
        return tree.getBestBoardForPlayer(currentBoard, playSymbol);
    }


    public static class BoardTree {
        private Logger logger = LoggerFactory.getLogger(getClass());
        private final PlayerToken player1;
        private final PlayerToken player2;

        public BoardTree(PlayerToken p1, PlayerToken p2) {
            player1 = p1;
            player2 = p2;
        }

        public Board getBestBoardForPlayer(Board currentBoard, PlayerToken player) {

            // TODO would prefer to get player as current player instead of from argument
            // but I think this should work for now

            // TODO maybe want to just get the next move/slot and return that instead of the board
            // rework Player to return desired slot instead of a new board.
            // we can just diff getAvailableMoves() from current and next boards

            Node<Board> gameTree = buildGameTree(new Node<>(currentBoard), player);

            int movesToClosestWin = Integer.MAX_VALUE;
            Board nextBoard = gameTree.getChildren().iterator().next().getContent();

            // TODO can we do this loop with a stream?

            // FIXME countMoves came back as 0 when it should have been 1.
            // issue is that countMoves does not calculate the actual number of moves for that player,
            // just the difference between ALL moves available and moves made
            // should clean this up so it's more meaningful, might fix the play bug (where it's losing) too.

            for(Node<Board> child : gameTree.getChildren()) {
                int currentMovesToWin = countMovesToClosestWin(child, player);
                if(currentMovesToWin < movesToClosestWin) {
                    logger.debug("setting moves to closest win to " + currentMovesToWin);
                    logger.debug("for board ");
                    logger.debug(child.getContent().toString());
                    nextBoard = child.getContent();
                    movesToClosestWin = currentMovesToWin;
                }
            }
            return nextBoard;
        }

        /**
         *
         * @param currentBoard should already have its content set
         * @param player player to use for all children of root
         * @return root that's populated.
         */
        public Node<Board> buildGameTree(Node<Board> currentBoard, PlayerToken player) {
            PlayerToken nextPlayer = nextPlayer(player);

            // TODO with all the references to root, maybe this should be moved into that class
            // also if the tree is rebuilt with every move, don't need to instantiate entire tree before traversing
            // can just instantiate and check board as you traverse a virtual tree

            currentBoard.setChildren(currentBoard.asNodes(getAvailableMoves(currentBoard.getContent(), player)));
            currentBoard.getChildren().forEach(c -> buildGameTree(c, nextPlayer));
            return currentBoard;
        }

        public PlayerToken nextPlayer(PlayerToken previousPlayer) {
            return previousPlayer.equals(player1) ? player2 : player1;
        }

        public long getNumberWinningBoards(Node<Board> node, PlayerToken player) {
            return node.stream()
                    .map(Node::getContent)
                    .filter(b -> b.isWinner(player))
                    .count();
        }

        public int countMovesToClosestWin(Node<Board> node, PlayerToken player) {
            final int rootNumMovesAvailable = node.getContent().getAvailableMoveCount();
            OptionalInt distance = node.stream()
                    .map(Node::getContent)
                    .filter(b -> b.isWinner(player))
                    .mapToInt(b -> rootNumMovesAvailable - b.getAvailableMoveCount())
                    .min();
            return distance.orElse(Integer.MAX_VALUE);
        }

        public Set<Board> getAvailableMoves(Board parent, PlayerToken player) {
            return parent.getAvailableMoves()
                    .stream()
                    .map(c -> parent.withPlay(c, player))
                    .collect(Collectors.toSet());
        }

    }

    public static class Node<T> {

        private final T content;
        private final Set<Node<T>> children = new HashSet<>();

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
            return contents.stream().map(t -> new Node<>(t)).collect(Collectors.toSet());
        }

        /**
         * Can use a stream to map, filter, reduce, anyMatch, collect...
         *
         * e.g. get all values in a list:
         * t.stream().map(Tree::getContent).collect(toList());
         *
         * @return all nodes in the tree including this
         */
        public Stream<Node<T>> stream() {

            // TODO this is not lazy and  is called for each node in the tree every time.
            // It probably could be improved using a Supplier
            // https://docs.oracle.com/javase/8/docs/api/java/util/stream/StreamSupport.html
            // https://docs.oracle.com/javase/8/docs/api/java/util/Spliterators.AbstractSpliterator.html

            return Stream.concat(Stream.of(this), children.stream().flatMap(Node::stream));
        }
    }

}
