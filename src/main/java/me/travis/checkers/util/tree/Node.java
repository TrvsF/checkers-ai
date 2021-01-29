package me.travis.checkers.util.tree;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;
import me.travis.checkers.logic.Moves;
import me.travis.checkers.util.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * node class for the game theory tree
 * TODO : create a rating system that isnt completely shite
 */
public class Node {

    private final Man[][] value;

    private final List<Node> children;

    private final int team;

    public Node(Man[][] value, int team) {
        this.value = value;
        this.team = team;
        this.children = new ArrayList<>();
    }

    public Node(Man[][] value, int team, List<Node> children) {
        this.value = value;
        this.team = team;
        this.children = children;
    }

    public Node(Man[][] value, int team, Node... children) {
        this.value = value;
        this.team = team;
        this.children = Arrays.asList(children);
    }

    public int rate() {
        int score = 0;

        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {

                Man man = value[i][j];

                // we dont really care about blank pieces
                if (man.getTeam() == 0) continue;

                // if piece is apart of AI's team
                if (man.getTeam() == this.team) {

                    score += 10;

                    // if the piece is progressing across the board rate higher
                    if (this.team == -1 && i > 3 || this.team == 1 && i < 5) {
                        score += 2;
                    }

                    List<Tuple<Integer, Integer, List<Man>>> moves = Moves.getMoves(i, j);

                    if (moves != null) {
                        score += 1;
                        for (Tuple<Integer, Integer, List<Man>> tuple : moves) {
                            if (tuple.getElement3() != null) {
                                score += 5;
                            }
                        }
                    }

                }

                // if piece isn't apart of AI's team
                if (man.getTeam() == this.team * -1) {
                    score -= 10;

                    // if the piece is progressing across the board rate higher
                    if (this.team == -1 && i < 3 || this.team == 1 && i > 5) {
                        score -= 2;
                    }
                }

            }
        }

        return score;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public Man[][] getValue() {
        return this.value;
    }

    public int getTeam() {
        return this.team;
    }

    public List<Node> getChildren() {
        return this.children;
    }

}
