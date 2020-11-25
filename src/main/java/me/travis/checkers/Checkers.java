package me.travis.checkers;

import me.travis.checkers.board.Board;
import me.travis.checkers.gui.Window;

/*
 * main class of the program
 */
public class Checkers {

    private static Window WINDOW;

    public static void main(String[] args) {
        System.out.println("STARTING CHECKERS...");
        Board.printDebugBoard();
        WINDOW = new Window();
//        Board.BOARD[3][2] = new Man(1);
//        Moves.getMovesDebug(2, 1);
    }

    public static Window getWindow() {
        return WINDOW;
    }

}

