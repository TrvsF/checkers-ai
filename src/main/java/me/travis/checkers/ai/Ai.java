package me.travis.checkers.ai;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;
import me.travis.checkers.logic.Moves;
import me.travis.checkers.util.Tuple;
import me.travis.checkers.util.tree.Node;
import me.travis.checkers.util.tree.Tree;

import java.util.List;

/**
 * handles the AI portion
 */
public class Ai {

    private Tree tree;
    private int depth;

    public Ai(int depth) {
        this.depth = depth;
    }

    /**
     * populates a tree of all valid moves to a certain depth
     * @param team the team of the AI
     */
    public void doAi(int team) {
        Node root = new Node(Board.BOARD, team);
        this.tree = new Tree(root);

        this.doAiR(team, 0, root);
    }

    public void doAiR(int team, int depth, Node parent) {

        if (this.depth >= depth) return;

        for (int i = 0; i < Board.BOARD.length; i++) {
            for (int j = 0; j < Board.BOARD[i].length; j++) {

                Man man = Board.BOARD[i][j];
                if (man.getTeam() == team) {
                    List<Tuple<Integer, Integer, List<Man>>> listOfMoves = Moves.getMoves(i, j);

                    if (listOfMoves != null) {
                        for (Tuple<Integer, Integer, List<Man>> move : listOfMoves) {
                            // reversed bc board reverse (is and js init)
                            Node child = new Node(Moves.simMovePieces(j, i, move.getElement2(), move.getElement1(), move.getElement3() != null), team);
                            parent.addChild(child);
                            this.doAiR(team, depth + 1, child);
                        }
                    }
                }
            }
        }
    }

}
