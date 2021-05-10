package me.travis.checkers;

import me.travis.checkers.gui.Window;
import me.travis.checkers.util.BoardUtil;

/**
 * main class of the program
 * @author travis faulkner, began work on the 19th of November
 */
public class Checkers {

    // Window (GUI) object
    private static Window WINDOW;

    // Game object
    private static Game GAME;

    public static void main(String[] args) {

        System.out.println("STARTING CHECKERS...");

        BoardUtil.printDebugBoard();

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
        WINDOW.refresh(true);
        WINDOW.refresh();

    }

    public static Window getWindow() {
        return WINDOW;
    }

    public static Game getGame() {
        return GAME;
    }

}

