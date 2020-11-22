package me.travis.checkers.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Piece extends JPanel {

    BufferedImage image;

    public Piece(BufferedImage image, int x, int y) {
        this.setOpaque(false); // so the png works (why isn't this enabled by default??)
        this.image = image;
        setPos(x, y);
    }

    public void setPos(int x, int y) {
        this.setBounds(x, y, 50, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
