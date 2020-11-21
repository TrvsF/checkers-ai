package me.travis.checkers.logic;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;
import me.travis.checkers.util.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * handles the logic of were the pieces may move
 * only handles the board object stored in the Board class, nothing with GUI is done here
 */
public class Moves {

    // list of moves object, a list of all the possible moves, contains their x, y, and if the jump is 'deadly'
    private static final List<Tuple<Integer, Integer, Boolean>> LIST_OF_MOVES = new ArrayList<>();

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

        int teamToMove = Board.BOARD[x1][y1].getTeam();
        int teamToMoveTo = Board.BOARD[x2][y2].getTeam();

        // if the spot if vacant add to list of return true
        if (teamToMoveTo == 0) {
            LIST_OF_MOVES.add(Tuple.createPair(x2, y2, false));
            return true;
        }

        // returns true if the spot is deadly
        return teamToMoveTo == teamToMove * -1 && canJump(x1, y1, x2, y2);
    }

    /*
     * returns true if a given piece can jump over another and land 'safely'
     * also adds this move to the list of current moves for that piece
     */
    private static boolean canJump(int x1, int y1, int x2, int y2) {
        int x3 = (x2 - x1) * 2;
        int y3 = (y2 - y1) * 2;
        if (Board.BOARD[x3][y3].getTeam() == 0) {
            LIST_OF_MOVES.add(Tuple.createPair(x3, y3, true));
            return true;
        } return false;
    }

    /*
     * moves two pieces of the board
     */
    public static void movePieces(int x1, int y1, int x2, int y2) throws ArrayIndexOutOfBoundsException {
        Man temp = Board.BOARD[x1][y1];
        Board.BOARD[x1][y1] = Board.BOARD[x2][y2];
        Board.BOARD[x2][y2] = temp;
    }

    /*
     * gets all the valid moves for a give piece via coordinates on the board
     */
    public static List<Tuple<Integer, Integer, Boolean>> getMoves(int x, int y) {
        LIST_OF_MOVES.clear();

        Man man = Board.BOARD[x][y];

        if (man.getTeam() == 0) {
            return Collections.emptyList();
        }
        // if the piece is allowed to move downwards diagonally
        boolean down = man.getTeam() == -1 || man.getTeam() == 1 && man.isKing();
        // if the piece is allowed to move upwards diagonally
        boolean up = man.getTeam() == 1 || man.getTeam() == -1 && man.isKing();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 || j == 0) continue;
                if (i == 1 && !down || i == -1 && !up) continue;
                canMove(x, y, x + i, y + j);
            }
        }
        return LIST_OF_MOVES;
    }

    /*
     * prints the found moves to the console
     */
    public static void getMovesDebug(int x, int y) {
        List<Tuple<Integer, Integer, Boolean>> moves = getMoves(x, y);

        if (moves.isEmpty()) {
            System.out.println("no moves found");
            return;
        }

        for (Tuple<Integer, Integer, Boolean> tuple : moves) {
            System.out.println("x : " + tuple.getElement1() + " y : " + tuple.getElement2() + " is deadly : " + tuple.getElement3());
        }
    }

}
