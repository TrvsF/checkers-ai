package me.travis.checkers.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * panel object that shows the board
 */
public class VBoard extends JPanel {

    private BufferedImage board;

    public VBoard() {
        setImage();
        this.setBounds(280, 160, 400, 400);
    }

    // sets the image to board.png, will draw nothing if there is an error loading
    private void setImage() {
        BufferedImage board_;
        try {
            board_ = ImageIO.read(new File("src/main/resources/board.png"));
        } catch (IOException ignored) {
            System.out.println("ERROR LOADING BOARD");
            board_ = null;
        }
        this.board = board_;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(board, 0, 0, this);
    }


}
