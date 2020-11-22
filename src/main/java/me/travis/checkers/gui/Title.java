package me.travis.checkers.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * label to display the title card at the top of the gui
 */
public class Title extends JPanel {

    private final Border BORDER = BorderFactory.createLineBorder(new Color(0x000000), 5);
    private BufferedImage TITLE;

    public Title() {
        this.setImage();
        this.setBounds(440, 20, 480, 120);
        this.setBorder(BORDER);
    }

    // sets the image to title.png, will draw nothing if there is an error loading
    private void setImage() {
        BufferedImage title;
        try {
            title = ImageIO.read(new File("src/main/resources/title.png"));
        } catch (IOException ignored) {
            System.out.println("ERROR LOADING BOARD");
            title = null;
        }
        this.TITLE = title;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(TITLE, 0, 0, this);
    }

}
