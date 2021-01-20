package me.travis.checkers;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;
import me.travis.checkers.gui.Window;

/**
 * main class of the program
 * @author travis faulkner, began work on the 19th of November
 */
public class Checkers {

    private static Window WINDOW;

    private static Game GAME;

    public static void main(String[] args) {

        System.out.println("STARTING CHECKERS...");

        Board.printDebugBoard();

        System.out.println("CREATING GUI...");
        WINDOW = new Window();

        int mode = 0;
        System.out.println("SETTING UP GAME IN MODE : " + mode);
        GAME = new Game(mode);

        WINDOW.setSubTitle("GAME");

        Board.BOARD[3][2].makeKing();
        Board.BOARD[2][3].makeKing();

        Board.BOARD[3][4] = new Man(1);
        Board.BOARD[5][6] = new Man(1);
        Board.BOARD[4][5].makeNull();
        Board.BOARD[6][7].makeNull();

        WINDOW.refresh(true);
        WINDOW.refresh();
    }

    public static Window getWindow() {
        return WINDOW;
    }

    public static Game getGame() {
        return GAME;
    }

}

