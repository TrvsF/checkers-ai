package me.travis.checkers.board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * class used to hold the information on each piece
 * called man bc the wiki referred to the pieces of a checkers game as 'men'
 */
public class Man {

    private int team;
    private boolean isKing;
    private String colourPath;
    private BufferedImage image;

    public List<Man> piecesToKill;

    public Man(int team) {
        this.team = team;
        this.isKing = false;
        this.setColourPath();
    }

    /**
     * sets the image of the man
     */
    private void setImage() {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("src/main/resources/" + this.colourPath + ".png"));
        } catch (IOException exception) {
            System.out.println("ERROR LOADING PIECE - TRAVIS PLEASE : " + exception);
            img = null;
        }
        this.image = img;
    }

    /**
     * sets the path of the image to whatever image should be displayed based on team
     */
    private void setColourPath() {
        if (this.team == 1) {
            this.colourPath = "white";
        } else if (this.team == -1) {
            this.colourPath = "black";
        } else if (this.team >= 9) {
            this.colourPath = "highlight";
        } else {
            this.colourPath = "blank";
        }
        this.setImage();
    }

    /**
     * @return the image of the man
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * @return the team of the man
     */
    public int getTeam() {
        return this.team;
    }

    /**
     * @return if the man is a king
     */
    public boolean isKing() {
        return this.isKing;
    }

    /**
     * change the image to the king variant
     * set isKing to true
     */
    public void makeKing() {
        this.colourPath += "_k";
        this.isKing = true;
        this.setImage();
    }

    /**
     * makes the position empty
     */
    public void makeNull() {
        this.team = 0;
        this.isKing = false;
        this.setColourPath();
    }

    /**
     * makes the position a highlight
     */
    public void makeHighlight() {
        this.team = 9;
        this.isKing = false;
        this.setColourPath();
    }

    public void makeDeadlyHighlight(List<Man> piecesToKill) {
        this.piecesToKill = piecesToKill;
        this.team = 10;
        this.isKing = false;
        this.setColourPath();
    }

}
