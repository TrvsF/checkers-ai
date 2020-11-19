package me.travis.checkers.gui;

import javax.swing.*;
import java.awt.*;

/*
 * class for the main window of the GUI
 * when object is create it defaults to showing the menu of the program
 */
public class Window extends JFrame {

    private final ImageIcon ICON = new ImageIcon("src/main/resources/logo.png");

    public Window() {
        initWindow();
        this.add(new Title());
    }

    private void initWindow() {
        this.setSize(960, 640);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Checkers : Menu");
        this.getContentPane().setBackground(new Color(0xE0E0E0));
        this.setIconImage(ICON.getImage());
        this.setVisible(true);
    }

}
