package me.travis.checkers.util.tree;

import me.travis.checkers.board.Man;
import me.travis.checkers.logic.Moves;
import me.travis.checkers.util.Pair;
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

    /**
     * Rates the given node based on pos, moves and how many pieces a given team has
     * (pos = better | neg = worse)
     * @return a rating of a given node
     */
    public int rate() {
        int totalScore = 0;
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                int score = 0;
                Man man = value[i][j];
                // we dont really care about blank pieces
                if (man.getTeam() == 0) continue;
                // if piece is apart of AI's team
                if (man.getTeam() == this.team) {
                    score += 10;
                    // if the piece is progressing across the board rate higher
                    if (this.team == -1 && i >= 4 || this.team == 1 && i <= 5) {
                        score += 3;
                    }
                    List<Tuple<Integer, Integer, List<Pair<Integer, Integer>>>> moves = Moves.getMovesAI(i, j, this.value);
                    if (!moves.isEmpty()) {
                        score += 1;
                        for (Tuple<Integer, Integer, List<Pair<Integer, Integer>>> tuple : moves) {
                            score += tuple.getElement3().size() * 3;
                        }
                    }
                }
                // if piece isn't apart of AI's team
                if (man.getTeam() == this.team * -1) {
                    score -= 10;
                    // if the piece is progressing across the board rate higher
                    if (this.team == -1 && i < 3 || this.team == 1 && i > 5) {
                        score -= 3;
                    }
                    List<Tuple<Integer, Integer, List<Pair<Integer, Integer>>>> moves = Moves.getMovesAI(i, j, this.value);
                    if (!moves.isEmpty()) {
                        score -= 1;
                        for (Tuple<Integer, Integer, List<Pair<Integer, Integer>>> tuple : moves) {
                            score -= tuple.getElement3().size() * 3;
                        }
                    }
                }
                totalScore += score;
            }
        }
        return totalScore;
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
