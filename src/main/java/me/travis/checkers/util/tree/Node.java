package me.travis.checkers.util.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

    private final int[][] value;

    private final List<Node> children;

    private final int team;

    public Node(int[][] value, int team) {
        this.value = value;
        this.team = team;
        this.children = new ArrayList<>();
    }

    public Node(int[][] value, int team, List<Node> children) {
        this.value = value;
        this.team = team;
        this.children = children;
    }

    public Node(int[][] value, int team, Node... children) {
        this.value = value;
        this.team = team;
        this.children = Arrays.asList(children);
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public int[][] getValue() {
        return this.value;
    }

    public int getTeam() {
        return this.team;
    }

    public List<Node> getChildren() {
        return this.children;
    }

}
