package me.travis.checkers.gui;

import me.travis.checkers.Checkers;

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

    private final BufferedImage title = Checkers.banner;

    public Title() {
        this.setBounds(55, 20, 480, 120);
        Border BORDER = BorderFactory.createLineBorder(new Color(0x000000), 5);
        this.setBorder(BORDER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(title, 0, 0, this);
    }

}
