package me.travis.checkers.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Highlight extends JPanel {

    private BufferedImage HIGHLIGHT;

    public Highlight(int x, int y) {
        this.setImage();
        this.setBounds(x, y, 480, 120);
    }

    // sets the image to highlight.png, will draw nothing if there is an error loading
    private void setImage() {
        BufferedImage highlight;
        try {
            highlight = ImageIO.read(new File("src/main/resources/highlight.png"));
        } catch (IOException ignored) {
            System.out.println("ERROR LOADING BOARD");
            highlight = null;
        }
        this.HIGHLIGHT = highlight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(HIGHLIGHT, 0, 0, this);
    }

}
