package me.travis.checkers.util;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;

public class Arrays {

    /**
     * clones the board for use for AI stuffs
     * cant just do normal ways bc thats clone by reference and we want clone by value
     * so yknow
     * it doesnt move the board around
     * @return
     */
    public static Man[][] cloneBoard() {
        Man[][] clone = new Man[10][];
        for (int i = 0; i < 10; i++) {
            System.arraycopy(Board.BOARD[i], 0, clone[i], 0, 10);
        }
        return clone;
    }

}
