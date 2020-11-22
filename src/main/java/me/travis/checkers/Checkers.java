package me.travis.checkers;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;
import me.travis.checkers.gui.Window;
import me.travis.checkers.logic.Moves;

/*
 * main class of the program
 */
public class Checkers {

    public static void main(String[] args) {
        System.out.println("STARTING CHECKERS...");
        Board.printDebugBoard();
        new Window();
//        Board.BOARD[3][2] = new Man(1);
//        Moves.getMovesDebug(2, 1);
    }

}

