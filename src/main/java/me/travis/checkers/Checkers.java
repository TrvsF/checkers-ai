package me.travis.checkers;

import me.travis.checkers.gui.Window;

/**
 * main class of the program
 */
public class Checkers {

    private static Window WINDOW;

    private static Game GAME;

    public static void main(String[] args) {

        System.out.println("STARTING CHECKERS...");

        System.out.println("CREATING GUI...");
        WINDOW = new Window();

        // 0 = pvp
        // 1 = pva
        // 2 = ava
        int mode = 0;
        System.out.println("SETTING UP GAME IN MODE : " + mode);
        GAME = new Game(mode, 1);

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

