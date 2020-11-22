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

    public Window() {
        initPieces();
        this.add(new Title());
        this.add(new VBoard());
        initWindow();
    }

    private void initWindow() {
        this.setSize(960, 640);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Checkers : Menu");
        this.getContentPane().setBackground(new Color(0x4F4F4F));
        this.setIconImage(ICON.getImage());
        this.setVisible(true);
    }

    private void initPieces() {
        int x = 280;
        int y = 160;
        int padding = 50;
        for (Man[] men : Board.BOARD) {
            for (Man man : men) {
                if (man.getTeam() == 1) {
                    this.add(new Piece("white", x, y));
                    System.out.println("drawing piece @ " + x + " " + y);
                }
                if (man.getTeam() == -1) {
                    this.add(new Piece("black", x, y));
                    System.out.println("drawing piece @ " + x + " " + y);
                }
                x += padding;
            }
            y += padding;
            x = 280;
        }
    }

}
