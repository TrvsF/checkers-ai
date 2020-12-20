package me.travis.checkers;

import me.travis.checkers.board.Board;
import me.travis.checkers.logic.Misc;
import me.travis.checkers.logic.Moves;
import me.travis.checkers.util.Tuple;

import java.util.List;

public class Game {

    private int turn;

    private int[] selectedMan;

    private final boolean whiteAI;
    private final boolean blackAI;
    private boolean gameOver;

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
        this.selectedMan = null;

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
     * handles when a piece is clicked on the board
     * @param x of what piece has been clicked
     * @param y of what piece has been clicked
     * @param team of what piece has been clicked
     */
    public void handleClick(int x, int y, int team) {
        // clears the currently drawn highlights
        Checkers.getWindow().clearHighlights();

        System.out.println("------------------------");

        System.out.println("you just clicked a piece with team : " + team);

        // get the coords of the board (flipped bc nested arrays are backward)
        int[] relative = Misc.guiToBoard(x, y);

        // if a highlight is selected
        if (team == 9 && this.selectedMan != null) {

            // move the pieces
            System.out.println("you have selected a valid move, making pieces now");
            Moves.movePieces(relative[1], relative[0], selectedMan[0], selectedMan[1]);

            // refresh the window
            Checkers.getWindow().refresh();

            // move to the next turn
            this.nextTurn();

            return;
        }

        // if it isnt the turn of the team clicked
        if (team != this.turn ) {
            System.out.println("it is not this team's turn");
            return;
        }

        // displays what has just been clicked to the user
        System.out.println("X : " + relative[0] + " | " + x);
        System.out.println("Y : " + relative[1] + " | " + y);
        System.out.println("------------------------");
        // flipped bc nested arrays are backward - gets the moves that piece can move to
        System.out.println("VALID MOVES : ");

        Moves.getMovesDebug(relative[1], relative[0]);

        System.out.println("------------------------");

        List<Tuple<Integer, Integer, Boolean>> moves = Moves.getMoves(relative[1], relative[0]);

        // if there are no moves we don't care about that piece
        if (moves.isEmpty()) {
            return;
        }

        // if there is, store the given position so we can move it later
        this.selectedMan = new int[]{relative[1], relative[0]};

        System.out.println("drawing moves now...");

        // for each new place to highlight
        for (Tuple<Integer, Integer, Boolean> tuple : moves) {
            // update the board to display the new highlights
            Board.BOARD[tuple.getElement1()][tuple.getElement2()].makeHighlight();
        }

        // refresh the GUI
        Checkers.getWindow().refresh();

        System.out.println("------------------------");

        Board.printDebugBoard();
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

    /**
     * clears the selected move
     */
    private void clearSelection() {
        this.selectedMan = null;
    }

}
