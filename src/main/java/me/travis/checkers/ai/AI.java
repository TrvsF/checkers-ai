package me.travis.checkers.ai;

import me.travis.checkers.board.Man;
import me.travis.checkers.logic.Moves;
import me.travis.checkers.util.BoardU;
import me.travis.checkers.util.Pair;
import me.travis.checkers.util.Tuple;
import me.travis.checkers.util.tree.Node;
import me.travis.checkers.util.tree.Tree;

import java.util.List;

/**
 * handles the AI portion of the program
 */
public class AI {

    private Tree tree;
    private final int team;
    private final int depth;
    private int children;

    public AI(int depth, int team) {
        this.depth = depth;
        this.team = team;
        this.children = 0;
    }

    public Tree getTree() {
        return this.tree;
    }

    public int getChildren() {
        return this.children;
    }

    /**
     * populates a tree of all valid moves to a certain depth
     */
    public void populate() {
        Node root = new Node(BoardU.cloneBoard(), this.team);
        this.tree = new Tree(root);
        this.children = 0;

        this.populateR(0, root, this.team);
    }

    private void populateR(int depth, Node parent, int team) {
        // don't want to go too far for memory & processing sake
        if (this.depth <= depth) return;

        // checks each piece of the board
        for (int i = 0; i < parent.getValue().length; i++) {
            for (int j = 0; j < parent.getValue()[i].length; j++) {

                Man man = parent.getValue()[i][j];
                // if the piece is apart of the AIs team
                if (man.getTeam() == team) {

                    // if the piece can move add all these moves as branches to the tree and recursively make new
                    // branches from these branches (its 7am pls)
                    List<Tuple<Integer, Integer, List<Pair<Integer, Integer>>>> listOfMoves = Moves.getMovesAI(i, j, parent.getValue());

                    for (Tuple<Integer, Integer, List<Pair<Integer, Integer>>> tuple : listOfMoves) {
                        Node child = new Node(Moves.simMovePieces(j, i, tuple.getElement2(), tuple.getElement1(), tuple.getElement3(), parent.getValue()), this.team);
                        parent.addChild(child);
                        this.children++;
                        this.populateR(depth + 1, child, team * -1);
                    }
                }
            }
        }
    }

    public Node getNode(int childNo) {
        return this.tree.getRoot().getChildren().get(childNo);
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

    /**
     * the min max method with AB pruning to ease memory useage, a better explanation of what this does
     * can be found in the paperwork
     * @param node Node to check
     * @param depth Depth of the current pass
     * @param a Alpha
     * @param b Beta
     * @return The best score
     */
    private int minMaxAB(Node node, int depth, int a, int b) {
        if (depth <= 0 || isTerminal(node)) {
            return node.rate();
        }
        if (node.getTeam() == this.team) {
            int currentA = Integer.MIN_VALUE;
            for (Node child : node.getChildren()) {
                currentA = Math.max(currentA, minMaxAB(child, depth - 1, a, b));
                a = Math.max(a, currentA);
                if (a >= b) {
                    return a;
                }
            }
            return currentA;
        }
        int currentB = Integer.MAX_VALUE;
        for (Node child : node.getChildren()) {
            currentB = Math.min(currentB, minMaxAB(child, depth - 1, a, b));
            b = Math.min(b, currentB);
            if (b <= a) {
                return b;
            }
        }
        return currentB;
    }

    /**
     * get the best move
     * @return The Board state of the best move
     */
    public Man[][] getBestMove() {
        if (isTerminal(this.tree.getRoot())) {
            System.out.println("NO CHILDREN, GAME SHOULD BE OVER");
            return null;
        }
        Man[][] bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        for (Node child : this.tree.getRoot().getChildren()) {
            int a = minMaxAB(child, this.depth, bestScore, Integer.MAX_VALUE);
            if (a > bestScore || bestMove == null) {
                bestMove = child.getValue();
                bestScore = a;
            }
        }
        return bestMove;
    }

    /**
     * @param node Node to check
     * @return if the node has any children
     */
    private boolean isTerminal(Node node) {
        return node.getChildren().isEmpty();
    }

}
