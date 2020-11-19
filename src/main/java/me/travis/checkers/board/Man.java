package me.travis.checkers.board;

/*
 * class used to hold the information on each piece
 * called man bc the wiki referred to the pieces of a checkers game as 'men'
 */
public class Man {

    private final int id;
    private boolean isKing;
    // TODO : LINK TO IMG

    public Man(int id) {
        this.id = id;
        this.isKing = false;
    }

    public int getId() {
        return this.id;
    }

    public boolean isKing() {
        return this.isKing;
    }

    public void makeKing() {
        this.isKing = true;
    }

}
