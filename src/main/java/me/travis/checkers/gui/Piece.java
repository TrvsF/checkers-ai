package me.travis.checkers.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece extends JPanel {

    private BufferedImage PIECE;

    public Piece(String colour, int x, int y) {
        this.setOpaque(false);
        setImage(colour);
        setPos(x, y);
    }

    public void setPos(int x, int y) {
        this.setBounds(x, y, 50, 50);
    }

    // sets the image to piece.png, will draw nothing if there is an error loading
    private void setImage(String colour) {
        BufferedImage piece;
        try {
            piece = ImageIO.read(new File("src/main/resources/" + colour + ".png"));
        } catch (IOException ignored) {
            System.out.println("ERROR LOADING PIECE");
            piece = null;
        }
        this.PIECE = piece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(PIECE, 0, 0, this);
    }

}
