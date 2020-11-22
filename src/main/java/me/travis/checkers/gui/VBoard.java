package me.travis.checkers.gui;

import me.travis.checkers.board.Board;
import me.travis.checkers.board.Man;

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

    private BufferedImage BOARD;

    public VBoard() {
        setImage();
        this.setBounds(280, 160, 400, 400);
        // initPieces();
    }

    // sets the image to board.png, will draw nothing if there is an error loading
    private void setImage() {
        BufferedImage board;
        try {
            board = ImageIO.read(new File("src/main/resources/board.png"));
        } catch (IOException ignored) {
            System.out.println("ERROR LOADING BOARD");
            board = null;
        }
        this.BOARD = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BOARD, 0, 0, this);
    }

}
