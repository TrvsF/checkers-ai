package me.travis.checkers.logic;

import me.travis.checkers.gui.Window;

public class Game {

    public static int[] guiToBoard(int x, int y) {
        return new int[]{(x - Window.PIECE_START_X) / Window.PIECE_PADDING, (y - Window.PIECE_START_Y) / Window.PIECE_PADDING};
    }

}
