package me.travis.checkers.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/*
 * label to display the title card at the top of the gui
 */
public class Title extends JLabel {

    private final Border BORDER = BorderFactory.createLineBorder(new Color(0x088F95), 5);
    private final ImageIcon TITLE = new ImageIcon("src/main/resources/title.png");

    public Title() {
        initTitle();
    }

    private void initTitle() {
        this.setIcon(TITLE);
        this.setBounds(240, 20, 480, 120);
        this.setBorder(BORDER);
    }

}
