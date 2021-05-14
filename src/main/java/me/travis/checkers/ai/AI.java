package me.travis.checkers.ai;

import me.travis.checkers.board.Man;
import me.travis.checkers.logic.Misc;
import me.travis.checkers.logic.Moves;
import me.travis.checkers.util.BoardUtil;
import me.travis.checkers.util.Pair;
import me.travis.checkers.util.Tuple;
import me.travis.checkers.util.Util;
import me.travis.checkers.util.tree.Node;
import me.travis.checkers.util.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * handles the AI portion of the program
 */
public class AI {

    private final List<Man[][]> alreadySeen = new ArrayList<>();

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
        System.out.println("STARTING POPULATION WITH DEPTH : " + this.depth + "\nFOR TEAM : " + this.team);

        System.out.println("STARTING BOARD : ");

        BoardUtil.printDebugBoard(BoardUtil.cloneBoard());

        Node root = new Node(BoardUtil.cloneBoard(), this.team);
        this.tree = new Tree(root);

        this.alreadySeen.clear();

        this.populateR(0, root, this.team);
    }

    /**
     * Populates the tree recursively
     * @param depth The current depth of the loop
     * @param parent The Node to get the children for
     * @param team The team that the pieces are moving for
     */
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
                    for (Tuple<Integer, Integer, List<Pair<Integer, Integer>>> tuple : Moves.getMovesAI(
                            i, j, parent.getValue())) {
                        Node child = new Node(Moves.simMovePieces(i, j, tuple.getElement1(), tuple.getElement2(),
                                tuple.getElement3(), parent.getValue()), this.team);
                        if (BoardUtil.isSame(alreadySeen, child.getValue())) {
                            System.out.println("SEEN");
                            break;
                        }
                        alreadySeen.add(BoardUtil.cloneBoard(child.getValue()));
                        parent.addChild(child);
                        this.children++;
                        this.populateR(depth + 1, child, team * -1);
                    }
                }
            }
        }
    }

    /**
     * repopulates the tree from a given node
     * @param node the node to repopulate from
     */
    public void repopulate(Node node) {
        this.tree.setRoot(node);
        this.repopulateR(node, this.depth);
    }

    /**
     * repopulates the tree from the root of a given board state
     * @param b the give board state
     */
    public void repopulate(Man[][] b) {
        for (Node node : this.tree.getRoot().getChildren()) {
            if (Util.isArrayEqual(node.getValue(), b)) {
                this.tree.setRoot(new Node(node));
                this.repopulateR(node, this.depth);
                return;
            }
        }
    }

    private int countChildrenR(Node node, int count) {
        if (node.getChildren() == null) return 0;
        for (Node child : node.getChildren()) {
            countChildrenR(child, count + 1);
        }

    }

    /**
     * the min max method with AB pruning to ease memory usage, a better explanation of what this does
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
     * @return a random first move (to keep it interesting)
     */
    public Node getFirstMove() {
        this.populate();
        if (this.isTerminal(this.tree.getRoot())) {
            System.out.println("NO CHILDREN, NOT GOOD");
            return null;
        }
        return Util.getRandomMove(this.tree.getRoot().getChildren());
    }

    /**
     * get the best move
     * @return The Board state of the best move
     */
    public Node getBestMove(Man[][] currentState) {
        // repopulate the tree
        this.repopulate(currentState);

        if (this.isTerminal(this.tree.getRoot())) {
            System.out.println("NO CHILDREN, GAME SHOULD BE OVER");
            return null;
        }
        Node bestMove = null;
        int bestRating = Integer.MIN_VALUE;
        for (Node child : this.tree.getRoot().getChildren()) {
            int a = minMaxAB(child, this.depth, bestRating, Integer.MAX_VALUE);
            if (a > bestRating || bestMove == null) {
                bestMove = child;
                bestRating = a;
            }
        }
        System.out.println("BEST MOVE HAS A RATING OF : " + bestRating);
        return bestMove;
    }

    /**
     * get random move
     * @return The board state of a random move
     */
    public Man[][] getRandomMove() {
        if (isTerminal(this.tree.getRoot())) {
            System.out.println("NO CHILDREN, GAME SHOULD BE OVER");
            return null;
        }
        return this.tree.getRoot().getChildren().get(Misc.getRandomIndexFromList(this.tree.getRoot().getChildren())).getValue();
    }

    /**
     * @param node Node to check
     * @return if the node has any children
     */
    private boolean isTerminal(Node node) {
        return node.getChildren().isEmpty();
    }

}
