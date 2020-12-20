package me.travis.checkers.gui;

import me.travis.checkers.Checkers;
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

    private final List<Piece> PIECES = new ArrayList<>();

    public static final int PIECE_START_X = 40;

    public static final int PIECE_START_Y = 160;

    public static final int PIECE_PADDING = 50;

    public Window() {
        this.drawPieces();
        this.add(new Title());
        //this.add(new VBoard());
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
        int x = PIECE_START_X;
        int y = PIECE_START_Y;

        for (Man[] men : Board.BOARD) {
            for (Man man : men) {
                Piece piece = new Piece(man.getImage(), x, y, man.getTeam());
                this.add(piece);
                this.PIECES.add(piece);
                if (man.getTeam() == 9) {
                    System.out.println("drawing highlight");
                }
                x += PIECE_PADDING;
            }
            y += PIECE_PADDING;
            x = PIECE_START_X;
        }
    }

    public void clearPieces() {
        if (this.PIECES.isEmpty()) return;

        for (Piece piece : this.PIECES) {
            this.remove(piece);
        }

        this.PIECES.clear();

        this.drawPieces();
    }

    public void clearHighlights() {
        Board.clearHighlights();
        this.refresh();
    }

    public void refresh() {
        System.out.println("refreshing... ");
        SwingUtilities.updateComponentTreeUI(this);
        this.clearPieces();
        // this.drawPieces();
    }

}
