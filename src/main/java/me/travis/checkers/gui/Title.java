package me.travis.checkers.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Title extends JLabel {

    private final Border BORDER = BorderFactory.createLineBorder(new Color(0x088F95), 5);
    private final ImageIcon TITLE = new ImageIcon("src/main/resources/title.png");

    public Title() {
        setUp();
    }

    private void setUp() {
        this.setIcon(TITLE);
        this.setText("made by travis");
        this.setVerticalAlignment(this.TOP);
        this.setHorizontalAlignment(this.CENTER);
        this.setVerticalTextPosition(this.BOTTOM);
        this.setHorizontalTextPosition(this.CENTER);
        this.setBorder(BORDER);
    }

}
