package me.travis.checkers.logic;

import me.travis.checkers.gui.Window;

import java.util.List;
import java.util.Random;

/*
 * handles the logic of misc parts of the game
 */
public class Misc {

    private static Random random = new Random();

    /**
     * converts a given gui x and y to the location that represents on the play board
     * @param x X
     * @param y Y
     * @return the x and y of the board as an array
     */
    public static int[] guiToBoard(int x, int y) {
        return new int[]{(x - Window.PIECE_START_X) / Window.PIECE_PADDING, (y - Window.PIECE_START_Y) / Window.PIECE_PADDING};
    }

    public static int getRandomIndexFromList(List list) {
        return random.nextInt(list.size());
    }

}
