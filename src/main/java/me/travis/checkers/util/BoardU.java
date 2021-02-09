package me.travis.checkers.util;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;

public class BoardU {

    private static int count = 0;

    /**
     * clones the board for use for AI stuffs
     * cant just do normal ways bc thats clone by reference and we want clone by value
     * so yknow
     * it doesnt move the board around
     * @return clone of board
     */
    public static Man[][] cloneBoard() {
        Man[][] clone = new Man[10][];
        for (int i = 0; i < Board.BOARD.length; i++) {
            clone[i] = new Man[10];
            System.arraycopy(Board.BOARD[i], 0, clone[i], 0, Board.BOARD[i].length);
        }
        count++;
        return clone;
    }

    /**
     * prints out the board to the console
     * used for debugging (hence the name)
     */
    public static void printDebugBoard() {
        for (Man[] men : Board.BOARD) {
            for (Man man : men) {
                System.out.print((man.getTeam() < 0 || man.getTeam() > 9 ? " " : "  ") + man.getTeam());
            }
            System.out.print("\n");
        }
    }

    /**
     * prints out the board to the console
     * used for debugging (hence the name)
     */
    public static void printDebugBoard(Man[][] board) {
        for (Man[] men : board) {
            for (Man man : men) {
                System.out.print((man.getTeam() < 0 || man.getTeam() > 9 ? " " : "  ") + man.getTeam());
            }
            System.out.print("\n");
        }
    }

}
