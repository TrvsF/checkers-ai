package me.travis.checkers.logic;

import me.travis.checkers.board.Board;
import me.travis.checkers.gui.Window;

/*
 * handles the logic of misc parts of the game
 */
public class Misc {

    /**
     * converts a given gui x and y to the location that represents on the play board
     * @param x X
     * @param y Y
     * @return the x and y of the board as an array
     */
    public static int[] guiToBoard(int x, int y) {
        return new int[]{(x - Window.PIECE_START_X) / Window.PIECE_PADDING, (y - Window.PIECE_START_Y) / Window.PIECE_PADDING};
    }

    /**
     * checks if there are any pieces on the board that should be kings and makes them a king
     */
    public static void checkKings() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ( (Board.BOARD[i][j].getTeam() == -1 && i == 9 || Board.BOARD[i][j].getTeam() == 1 && i == 0 )
                        && !Board.BOARD[i][j].isKing()) {
                    Board.BOARD[i][j].makeKing();
                }
            }
        }
    }

}
