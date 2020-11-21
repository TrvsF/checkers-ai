package me.travis.checkers.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece extends JPanel {

    private BufferedImage piece;

    public Piece(String colour) {
        setImage(colour);
    }

    public Piece(String colour, int x, int y) {
        setImage(colour);
        setPos(x, y);
    }

    public void setPos(int x, int y) {
        this.setBounds(x, y, 50, 50);
    }

    // sets the image to piece.png, will draw nothing if there is an error loading
    private void setImage(String colour) {
        BufferedImage piece_;
        try {
            piece_ = ImageIO.read(new File("src/main/resources/" + colour + ".png"));
        } catch (IOException ignored) {
            System.out.println("ERROR LOADING PIECE");
            piece_ = null;
        }
        this.piece = piece_;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(piece, 0, 0, this);
    }

}
