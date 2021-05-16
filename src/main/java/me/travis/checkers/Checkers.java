package me.travis.checkers;

import me.travis.checkers.gui.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * main class of the program
 */
public class Checkers {

    public static ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    private static Window WINDOW;

    private static Game GAME;

    public static BufferedImage black;
    public static BufferedImage blackKing;
    public static BufferedImage white;
    public static BufferedImage whiteKing;
    public static BufferedImage banner;
    public static BufferedImage board;
    public static BufferedImage highlight;
    public static BufferedImage blank;

    public static void main(String[] args) {

        System.out.println("STARTING CHECKERS...");

        System.out.println("CREATING GUI...");
        try {
            initImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WINDOW = new Window();

        // 0 = pvp
        // 1 = pva
        // 2 = ava
        int mode = 1;
        System.out.println("SETTING UP GAME IN MODE : " + mode);
        GAME = new Game(mode, 3);
        System.out.println("DONE");

        WINDOW.refresh(true);
        WINDOW.refresh();

    }

    public static Window getWindow() {
        return WINDOW;
    }

    public static Game getGame() {
        return GAME;
    }

    public static void initImages() throws IOException {
        black = getImage("black.png");
        white = getImage("white.png");
        blackKing = getImage("black_k.png");
        whiteKing = getImage("white_k.png");
        banner = getImage("title.png");
        board = getImage("board.png");
        highlight = getImage("highlight.png");
        blank = getImage("blank.png");
    }

    private static BufferedImage getImage(String string) throws IOException {
        return ImageIO.read(Objects.requireNonNull(classLoader.getResource(string)));
    }

}

