package me.travis.checkers.gui;

import me.travis.checkers.Checkers;
import me.travis.checkers.board.Board;
import me.travis.checkers.logic.Game;
import me.travis.checkers.logic.Moves;
import me.travis.checkers.util.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;

/*
 * Piece panel that displays the piece on the board
 */
public class Piece extends JLayeredPane implements MouseListener {

    private final BufferedImage image;
    private final int team;

    public Piece(BufferedImage image, int x, int y, int team) {
        this.setOpaque(false); // so the png works (why isn't this enabled by default??)
        this.image = image;
        this.team = team;
        this.setPos(x, y);
        this.addMouseListener(this);
    }

    public void setPos(int x, int y) {
        this.setBounds(x, y, 50, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("------------------------");

        System.out.println("you just clicked a piece with team : " + this.team);

        // get the coords of the board (flipped bc nested arrays are backward)
        int[] relative = Game.guiToBoard(this.getX(), this.getY());

        // displays what has just been clicked to the user
        System.out.println("X : " + relative[0] + " | " + this.getX());
        System.out.println("Y : " + relative[1] + " | " + this.getY());
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

        System.out.println("drawing moves now...");

        // if there is moves then remove the old highlights and draw new ones
        for (Tuple<Integer, Integer, Boolean> tuple : moves) {
            Checkers.getWindow().clearHighlights();
            // flipped as in the gui we work with x horizontal and y vertical
            Board.BOARD[tuple.getElement1()][tuple.getElement2()].makeHighlight();
            Checkers.getWindow().refresh();
        }

        System.out.println("------------------------");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
