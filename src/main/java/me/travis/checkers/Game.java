package me.travis.checkers;

public class Game {

    private int turn;

    private boolean gameOver;
    private boolean whiteAI;
    private boolean blackAI;

    /**
     * sets up the start of the game
     * the turn is set to black
     * if mode is 0 it is player vs player
     * if mode is 1 it is player vs ai
     * if mode is 2 it is ai vs ai
     * @param mode what mode the game is going to be played in
     */
    public Game(int mode) {
        this.turn = -1;
        this.whiteAI = (mode == 2);
        this.blackAI = (mode == 1 || mode == 2);
        this.gameOver = false;
    }

    /**
     * @return if the game is over
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * @return who's turn it is
     */
    public int getTurnID() {
        return this.turn;
    }

    /**
     * changes who's turn it is
     */
    private void nextTurn() {
        this.turn *= -1;

        if (this.turn == 1 && whiteAI) {
            // do white AI turn
        }

        if (this.turn == -1 && blackAI) {
            // do black AI turn
        }
    }

}
