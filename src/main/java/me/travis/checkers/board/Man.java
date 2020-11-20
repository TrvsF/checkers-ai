package me.travis.checkers.board;

/*
 * class used to hold the information on each piece
 * called man bc the wiki referred to the pieces of a checkers game as 'men'
 */
public class Man {

    private final int team;
    private boolean isKing;
    // TODO : LINK TO IMG

    public Man(int team) {
        this.team = team;
        this.isKing = false;
    }

    public int getTeam() {
        return this.team;
    }

    public boolean isKing() {
        return this.isKing;
    }

    public void makeKing() {
        this.isKing = true;
    }

}
