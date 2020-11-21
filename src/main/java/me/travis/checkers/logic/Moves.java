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

    /**
     * checks if a piece can move to another and adds the move to the list of valid moves
     * @param x1 X of moving piece
     * @param y1 Y of moving piece
     * @param x2 X of spot to check
     * @param y2 Y of spot to check
     * @return if a give piece can move to another coordinate
     */
    public static boolean canMove(int x1, int y1, int x2, int y2) {

        if (isOutOfBounds(x1) || isOutOfBounds(x2) || isOutOfBounds(y1) || isOutOfBounds(y2)) {
            return false;
        }

        int teamToMove = Board.BOARD[x1][y1].getTeam();
        int teamToMoveTo = Board.BOARD[x2][y2].getTeam();

        // if the spot if vacant add to list of return true
        if (teamToMoveTo == 0) {
            LIST_OF_MOVES.add(Tuple.create(x2, y2, false));
            return true;
        }

        // returns true if the spot is deadly
        return teamToMoveTo == teamToMove * -1 && canJump(x1, y1, x2, y2);
    }

    /**
     * checks if a piece can jump over another and adds the move to the list of valid moves
     * @param x1 X of moving piece
     * @param y1 Y of moving piece
     * @param x2 X of the piece to be jumped
     * @param y2 Y of the piece to be jumped
     * @return if it is possible for the piece to be jumped
     */
    private static boolean canJump(int x1, int y1, int x2, int y2) {
        int x3 = (x2 - x1) + x2;
        int y3 = (y2 - y1) + y2;

        if (isOutOfBounds(x3) || isOutOfBounds(y3)) {
            return false;
        }

        if (Board.BOARD[x3][y3].getTeam() == 0) {
            LIST_OF_MOVES.add(Tuple.create(x3, y3, true));
            return true;
        } return false;
    }

    /**
     * @param i given coordinate
     * @return if a given coordinate will be out of bounds of the board
     */
    private static boolean isOutOfBounds(int i) {
        return i < 0 || i > 8;
    }

    /**
     * moves a piece of the board
     * @param x1 starting X
     * @param y1 starting Y
     * @param x2 end X
     * @param y2 end Y
     * @throws ArrayIndexOutOfBoundsException
     */
    public static void movePieces(int x1, int y1, int x2, int y2) throws ArrayIndexOutOfBoundsException {
        Man temp = Board.BOARD[x1][y1];
        Board.BOARD[x1][y1] = Board.BOARD[x2][y2];
        Board.BOARD[x2][y2] = temp;
    }

    /**
     * gets all valid moves for a given coordinate
     * @param x X
     * @param y Y
     * @return a list of moves (x, y, if the move is deadly [kills another piece])
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

    /**
     * prints all the moves for a given piece to console
     * @param x X
     * @param y Y
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
