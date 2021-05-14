package me.travis.checkers.util;

import com.rits.cloning.Cloner;
import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;

import java.util.List;

public class BoardUtil {

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
            for (int j = 0; j < clone[i].length; j++) {
                clone[i][j] = new Man(Board.BOARD[i][j]);
            }
        }
        return clone;
    }

    public static boolean isSame(List<Man[][]> list, Man[][] array) {
        for (Man[][] man : list) {
            if (isSame(man, array)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSame(Man[][] b1, Man[][] b2) {
        if (b1.length != b2.length) return false;
        for (int i = 0; i < b1.length; i++) {
            for (int j = 0; j < b1[i].length; j++) {
                if (!(b1[i][j].getTeam() == b2[i][j].getTeam())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * clones the board for use for AI stuffs
     * cant just do normal ways bc thats clone by reference and we want clone by value
     * so yknow
     * it doesnt move the board around
     * @return clone of board
     */
    public static Man[][] cloneBoard(Man[][] board) {
        long startTime = System.currentTimeMillis();
        Cloner cloner = new Cloner();
        System.out.println("Cloning took " + (System.currentTimeMillis() - startTime) + "ms");
        return cloner.deepClone(board);
//        Man[][] clone = new Man[10][];
//        for (int i = 0; i < board.length; i++) {
//            clone[i] = new Man[10];
//            for (int j = 0; j < clone[i].length; j++) {
//                clone[i][j] = new Man(board[i][j]);
//            }
//        }
//        System.out.println("Cloning took " + (System.currentTimeMillis() - startTime) + "ms");
//        return clone;
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
        System.out.print("\n");
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
        System.out.print("\n");
    }

}
