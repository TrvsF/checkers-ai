package me.travis.checkers.ai;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;
import me.travis.checkers.logic.Moves;
import me.travis.checkers.util.Tuple;
import me.travis.checkers.util.tree.Node;
import me.travis.checkers.util.tree.Tree;

import java.util.Iterator;
import java.util.List;

/**
 * handles the AI portion of the program
 */
public class AI {

    private Tree tree;
    private final int team;
    private final int depth;

    public AI(int depth, int team) {
        this.depth = depth;
        this.team = team;
    }

    public Tree getTree() {
        return this.tree;
    }

    /**
     * populates a tree of all valid moves to a certain depth
     */
    public void populate() {
        Node root = new Node(Board.BOARD, this.team);
        this.tree = new Tree(root);

        this.populateR(0, root);
    }

    private void populateR(int depth, Node parent) {
        // don't want to go too far for memory & processing sake
        if (this.depth <= depth) return;

        // checks each piece of the board
        for (int i = 0; i < Board.BOARD.length; i++) {
            for (int j = 0; j < Board.BOARD[i].length; j++) {

                Man man = Board.BOARD[i][j];
                // if the piece is apart of the AIs team
                if (man.getTeam() == this.team) {

                    // if the piece can move add all these moves as branches to the tree and recursively make new
                    // branches from these branches (its 7am pls)
                    List<Tuple<Integer, Integer, List<Man>>> listOfMoves = Moves.getMoves(i, j);

                    for (Tuple<Integer, Integer, List<Man>> tuple : listOfMoves) {
                        Node child = new Node(Moves.simMovePieces(j, i, tuple.getElement2(), tuple.getElement1(), tuple.getElement3() != null), this.team);
                        parent.addChild(child);
                        this.populateR(depth + 1, child);
                    }
                }
            }
        }
    }

    public int countChildren() {
        if (this.tree.getRoot() == null) return 0;
        return this.countChildrenR(this.tree.getRoot(), 0);
    }

    private int countChildrenR(Node node, int count) {
        if (node.getChildren() == null) return 0;
        for (Node child : node.getChildren()) {
            countChildrenR(child, count++);
        }
        return count;
    }

}
