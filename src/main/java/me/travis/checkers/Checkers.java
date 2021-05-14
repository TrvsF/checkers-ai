package me.travis.checkers;

import me.travis.checkers.gui.Window;
import me.travis.checkers.util.Util;

/**
 * main class of the program
 */
public class Checkers {

    // Window (GUI) object
    private static Window WINDOW;

    // Game object
    private static Game GAME;

    public static void main(String[] args) {

        System.out.println("STARTING CHECKERS...");

        Util.printDebugBoard();

        System.out.println("CREATING GUI...");
        // init gui
        WINDOW = new Window();

        // 0 = pvp
        // 1 = pva
        // 2 = ava
        // TODO : MAKE THIS AN INPUT OR SOMETHING
        int mode = 0;

        System.out.println("SETTING UP GAME IN MODE : " + mode);
        // init game
        GAME = new Game(mode);

        // ensure all pieces are correctly rendered
        WINDOW.refresh(true);
        WINDOW.refresh(false);

    }

    public static Window getWindow() {
        return WINDOW;
    }

    public static Game getGame() {
        return GAME;
    }

}

