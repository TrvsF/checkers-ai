package me.travis.checkers.gui;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
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
        this.add(new VBoard());
        this.initWindow();
    }

    /**
     * draws a 1280 x 720 window that will house all the panels that make up our UI
     * also sets the window title, its background, the icon etc
     */
    private void initWindow() {
        this.setSize(1080, 720);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Checkers : Menu");
        this.getContentPane().setBackground(new Color(0x4F4F4F));
        this.setIconImage(ICON.getImage());
        this.setVisible(true);
    }

    public void setSubTitle(String subTitle) {
        this.setTitle("Checkers : " + subTitle);
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

    /**
     * clears all the pieces on the board
     */
    public void redrawPieces() {
        if (this.PIECES.isEmpty()) return;

        int c = -1;
        for (Man[] men : Board.BOARD) {
            for (Man man : men) {
                c++;
                if (PIECES.get(c).getImage() == man.getImage()) continue;
                this.PIECES.get(c).setImage(man.getImage());
                this.PIECES.get(c).setTeam(man.getTeam());
                this.PIECES.get(c).repaint();
            }
        }
    }

    /**
     * clears the highlights
     * @param all specifies if it should clear the deadly ones or not, used so if a deadly move exists the player
     *            HAS to take that move
     */
    public void clearHighlights(boolean all) {
        if (all) {
            Board.clearAllHighlights();
        } else {
            Board.clearHighlights();
        }
    }

    /**
     * refreshes the board to draw the new pos of all the pieces
     */
    public void refresh(boolean hard) {
        if (hard) {
            SwingUtilities.updateComponentTreeUI(this);
        }
        this.redrawPieces();
    }


}
