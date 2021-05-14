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
        GAME = new Game(mode, 1);
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
        black = ImageIO.read(Objects.requireNonNull(classLoader.getResource("black.png")));
        white = ImageIO.read(Objects.requireNonNull(classLoader.getResource("white.png")));
        blackKing = ImageIO.read(Objects.requireNonNull(classLoader.getResource("black_k.png")));
        whiteKing = ImageIO.read(Objects.requireNonNull(classLoader.getResource("white_k.png")));
        banner = ImageIO.read(Objects.requireNonNull(classLoader.getResource("title.png")));
        board = ImageIO.read(Objects.requireNonNull(classLoader.getResource("board.png")));
        highlight = ImageIO.read(Objects.requireNonNull(classLoader.getResource("highlight.png")));
        blank = ImageIO.read(Objects.requireNonNull(classLoader.getResource("blank.png")));
    }

}

