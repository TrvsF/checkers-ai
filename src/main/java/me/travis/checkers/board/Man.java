package me.travis.checkers.board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * class used to hold the information on each piece
 * called man bc the wiki referred to the pieces of a checkers game as 'men'
 */
public class Man {

    private final int team;
    private boolean isKing;
    private String colourPath;
    private BufferedImage image;

    public Man(int team) {
        this.team = team;
        this.isKing = false;
        this.setColourPath();
        this.setImage();
    }

    private void setImage() {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("src/main/resources/" + this.colourPath + ".png"));
        } catch (IOException ignored) {
            System.out.println("ERROR LOADING PIECE");
            img = null;
        }
        this.image = img;
    }

    private void setColourPath() {
        if (this.team == 1) {
            this.colourPath = "white";
        } else if (this.team == -1) {
            this.colourPath = "black";
        } else {
            this.colourPath = "blank";
        }
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public int getTeam() {
        return this.team;
    }

    public boolean isKing() {
        return this.isKing;
    }

    public void makeKing() {
        this.colourPath += "_k";
        this.isKing = true;
        this.setImage();
    }

}
