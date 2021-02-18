package me.travis.checkers;

import me.travis.checkers.ai.AI;
import me.travis.checkers.gui.Window;
import me.travis.checkers.util.BoardU;

/**
 * main class of the program
 * @author travis faulkner, began work on the 19th of November
 */
public class Checkers {

    private static Window WINDOW;

    private static Game GAME;

    private static AI AI;

    public static void main(String[] args) {

        System.out.println("STARTING CHECKERS...");

        BoardU.printDebugBoard();

        System.out.println("CREATING GUI...");
        WINDOW = new Window();

        // 0 = pvp
        // 1 = pva
        // 2 = ava
        int mode = 1;
        System.out.println("SETTING UP GAME IN MODE : " + mode);
        GAME = new Game(mode);

//        Board.BOARD[3][2].makeKing();
//        Board.BOARD[2][3].makeKing();
//
//        Board.BOARD[3][4] = new Man(1);
//        Board.BOARD[5][6] = new Man(1);
//        Board.BOARD[4][5].makeNull();
//        Board.BOARD[6][7].makeNull();

        WINDOW.refresh(true);
        WINDOW.refresh();

//        AI = new AI(3, 1);
//
//        AI.populate();
//        System.out.println("children : " + AI.getChildren());
//        BoardU.printDebugBoard(AI.getNode(0).getValue());
//
//        BoardU.printDebugBoard(AI.getBestMove());

    }

    public static Window getWindow() {
        return WINDOW;
    }

    public static Game getGame() {
        return GAME;
    }

}

