package me.travis.checkers.gui;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;

import javax.swing.*;
import java.awt.*;

/*
 * class for the main window of the GUI
 * when object is create it defaults to showing the menu of the program
 */
public class Window extends JFrame {

    private final ImageIcon ICON = new ImageIcon("src/main/resources/logo.png");

    public static final int PIECE_START_X = 40;

    public static final int PIECE_START_Y = 160;

    public static final int PIECE_PADDING = 50;

    public Window() {
        this.initPieces();
        this.add(new Title());
        this.add(new VBoard());
        this.initWindow();
    }

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

    private void initPieces() {
        int x = PIECE_START_X;
        int y = PIECE_START_Y;
        for (Man[] men : Board.BOARD) {
            for (Man man : men) {
                this.add(new Piece(man.getImage(), x, y, man.getTeam()));
                System.out.println("drawing piece @ " + x + " " + y);
                x += PIECE_PADDING;
            }
            y += PIECE_PADDING;
            x = PIECE_START_X;
        }
    }

    public static void drawHighlights(int x, int y) {
        x = (x * PIECE_PADDING) + PIECE_START_X;
        y = (y * PIECE_PADDING) + PIECE_START_Y;

        JLabel hl = new JLabel();
        hl.setBounds(x, y, 50, 50);
        hl.setBackground(Color.BLUE);
    }

}
