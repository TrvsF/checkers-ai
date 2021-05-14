package me.travis.checkers.board;

import me.travis.checkers.Checkers;

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
    private BufferedImage image;

    public List<Man> piecesToKill;

    public Man(int team) {
        this.team = team;
        this.isKing = false;
        this.setImage();
    }

    public Man(Man man) {
        this.team = man.getTeam();
        this.isKing = man.isKing();
        this.setImage();
    }

    /**
     * sets the image of the man
     */
    private void setImage() {
        if (this.team == 1) {
            if (this.isKing) {
                this.image = Checkers.whiteKing;
                return;
            }
            this.image = Checkers.white;
        } else if (this.team == -1) {
            if (this.isKing) {
                this.image = Checkers.blackKing;
                return;
            }
            this.image = Checkers.black;
        } else if (this.team >= 9) {
            this.image = Checkers.highlight;
        } else {
            this.image = Checkers.blank;
        }
    }

    /**
     * @return the image of the man
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * sets team to given int
     * @param team new team
     */
    public void setTeam(int team) {this.team = team;}

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
        this.isKing = true;
        this.setImage();
    }

    /**
     * makes the position empty
     */
    public void makeNull() {
        this.team = 0;
        this.isKing = false;
        this.setImage();
    }

    /**
     * makes the position a highlight
     */
    public void makeHighlight() {
        this.team = 9;
        this.isKing = false;
        this.setImage();
    }

    public void makeDeadlyHighlight(List<Man> piecesToKill) {
        this.piecesToKill = piecesToKill;
        this.team = 10;
        this.isKing = false;
        this.setImage();
    }

}
