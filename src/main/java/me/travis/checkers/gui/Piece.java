package me.travis.checkers.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/*
 * Piece panel that displays the piece on the board
 */
public class Piece extends JPanel implements MouseListener {

    private final BufferedImage image;
    private final int team;

    public Piece(BufferedImage image, int x, int y, int team) {
        this.setOpaque(false); // so the png works (why isn't this enabled by default??)
        this.image = image;
        this.team = team;
        this.setPos(x, y);
        this.addMouseListener(this);
    }

    public void setPos(int x, int y) {
        this.setBounds(x, y, 50, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("you just clicked a piece with team : " + this.team);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
