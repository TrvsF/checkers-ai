package me.travis.checkers.gui;

import me.travis.checkers.Checkers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * panel object that shows the board
 */
public class VBoard extends JLayeredPane {

    private final BufferedImage board;

    public VBoard() {
        this.board = Checkers.board;
        this.setBounds(40, 160, 500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(board, 0, 0, this);
    }

}
