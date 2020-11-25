package me.travis.checkers.gui;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * class for the main window of the GUI
 * when object is create it defaults to showing the menu of the program
 */
public class Window extends JFrame {

    private final ImageIcon ICON = new ImageIcon("src/main/resources/logo.png");

    private final List<Highlight> HIGHLIGHTS = new ArrayList<>();

    private final List<Piece> PIECES = new ArrayList<>();

    public static final int PIECE_START_X = 40;

    public static final int PIECE_START_Y = 160;

    public static final int PIECE_PADDING = 50;

    public Window() {
        this.drawPieces();
        this.add(new Title());
        this.add(new VBoard());
        this.initWindow();
    }

    /**
     * draws a 1280 x 720 window that will house all the panels that make up our UI
     * also sets the window title, its background, the icon etc
     */
    private void initWindow() {
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Checkers : Menu");
        this.getContentPane().setBackground(new Color(0x4F4F4F));
        this.setIconImage(ICON.getImage());
        this.setVisible(true);
    }

    /**
     * draws the pieces of the board
     * from the master board object
     */
    private void drawPieces() {
        this.refresh();

        int x = PIECE_START_X;
        int y = PIECE_START_Y;

        for (Man[] men : Board.BOARD) {
            for (Man man : men) {
                Piece piece = new Piece(man.getImage(), x, y, man.getTeam());
                this.add(piece);
                this.PIECES.add(piece);
                System.out.println("drawing piece @ " + x + " " + y);
                x += PIECE_PADDING;
            }
            y += PIECE_PADDING;
            x = PIECE_START_X;
        }
    }

    /**
     * draws a highlight object at a specific position
     * @param x X
     * @param y Y
     */
    public void drawHighlights(int x, int y) {
        this.refresh();

        x = (x * PIECE_PADDING) + PIECE_START_X;
        y = (y * PIECE_PADDING) + PIECE_START_Y;

        System.out.println("adding hl @ " + x + ", " + y);
        Highlight hl = new Highlight(x, y);
        this.HIGHLIGHTS.add(hl);
    }

    public void clearPieces() {
        if (this.PIECES.isEmpty()) return;

        for (Piece piece : this.PIECES) {
            this.remove(piece);
        }
        this.PIECES.clear();
    }

    public void clearHighlights() {
        if (this.HIGHLIGHTS.isEmpty()) return;

        for (Highlight hl : this.HIGHLIGHTS) {
            this.remove(hl);
        }
        this.HIGHLIGHTS.clear();
    }

    private void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
    }

}
