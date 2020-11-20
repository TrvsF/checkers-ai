package me.travis.checkers.logic;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.internal.util.xml.impl.Pair;
import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;

import java.util.ArrayList;
import java.util.List;

/*
 * handles the logic of were the pieces may move
 * only handles the board object stored in the Board class, nothing with GUI is done here
 */
public class Moves {

    private static List<Pair> LIST_OF_MOVES = new ArrayList<>();

    /*
     * checks if two pieces on the board may move
     * returns true if so, false if not
     */
    public static boolean canMove(int x1, int y1, int x2, int y2) {

        // should never happen, jic
        if (x1 < 0 || x1 > 8) return false;
        if (y1 < 0 || y1 > 8) return false;
        // more likely to happen, so we dont get an oob error
        if (x2 < 0 || x2 > 8) return false;
        if (y2 < 0 || y2 > 8) return false;

        // int teamToMove = Board.BOARD[x1][y1].getTeam();
        int teamToMoveTo = Board.BOARD[x2][y2].getTeam();

        return teamToMoveTo == 0;
    }

    /*
     * moves two pieces of the board
     */
    public static void movePieces(int x1, int y1, int x2, int y2) throws ArrayIndexOutOfBoundsException {
        Man temp = Board.BOARD[x1][y1];
        Board.BOARD[x1][y1] = Board.BOARD[x2][y2];
        Board.BOARD[x2][y2] = temp;
    }

    public static List<Pair> getMoves(int x, int y) {
        return getMoves(Board.BOARD[x][y]);
    }

    private static List<Pair> getMoves(Man man) {
        LIST_OF_MOVES.clear();



        return LIST_OF_MOVES;
    }

}
