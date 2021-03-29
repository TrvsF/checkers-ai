package me.travis.checkers;

import me.travis.checkers.ai.AI;
import me.travis.checkers.gui.Window;
import me.travis.checkers.util.BoardU;

/**
 * main class of the program
 * @author travis faulkner, began work on the 19th of November
 */
public class Checkers {

    private static Window WINDOW;

    private static Game GAME;

    private static AI AI;

    public static void main(String[] args) {

        System.out.println("STARTING CHECKERS...");

        BoardU.printDebugBoard();

        System.out.println("CREATING GUI...");
        WINDOW = new Window();

        // 0 = pvp
        // 1 = pva
        // 2 = ava
        int mode = 1;
        System.out.println("SETTING UP GAME IN MODE : " + mode);
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

