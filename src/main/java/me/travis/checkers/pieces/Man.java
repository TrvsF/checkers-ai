package me.travis.checkers.pieces;

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
