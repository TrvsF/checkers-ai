package me.travis.checkers;

import me.travis.checkers.board.Board;

/*
 * main class of the program
 */
public class Checkers {

    public static void main(String[] args) {
        System.out.println("STARTING CHECKERS...");
        Board b = new Board();
        b.printDebugBoard();
    }

}

