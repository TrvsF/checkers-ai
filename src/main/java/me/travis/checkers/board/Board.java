package me.travis.checkers.board;

/**
 * class for holding all the information on the board, including the board itself
 * this doesn't really *have* to be a class, but its a lot nicer to have the board object in its own space
 */
public class Board {

    public static Man[][] BOARD = createBoard();

    /**
     * create the initial state of the board
     * populates a 2d array of 'Man' objects and assigns them their correct starting positions
     * @return the board object
     */
    private static Man[][] createBoard() {
        Man[][] board = new Man[10][];
        for (int i = 0; i < 10; i++) {
            board[i] = new Man[10];
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 != 0) {
                    if (i < 4) {
                        board[i][j] = new Man(-1);
                    } else if (i > 5) {
                        board[i][j] = new Man(1);
                    } else {
                        board[i][j] = new Man(0);
                    }
                } else {
                    board[i][j] = new Man(0);
                }
            }
        }
        return board;
    }

    public static void resetBoard() {
        BOARD = createBoard();
    }

    private static int getBlackCount() {
        int c = 0;
        for (Man[] men : BOARD) {
            for (Man man : men) {
                if (man.getTeam() == -1) {
                    c++;
                }
            }
        }
        return c;
    }

    private static int getWhiteCount() {
        int c = 0;
        for (Man[] men : BOARD) {
            for (Man man : men) {
                if (man.getTeam() == 1) {
                    c++;
                }
            }
        }
        return c;
    }

    public static boolean shouldGameFinish() {
        return getBlackCount() == 0 || getWhiteCount() == 0;
    }

    /**
     * clears the non-deadly highlights
     */
    public static void clearHighlights() {
        for (Man[] men : BOARD) {
            for (Man man : men) {
                if (man.getTeam() == 9) {
                    man.makeNull();
                }
            }
        }
    }

    /**
     * clears ALL the highlights
     */
    public static void clearAllHighlights() {
        for (Man[] men : BOARD) {
            for (Man man : men) {
                if (man.getTeam() >= 9) {
                    man.makeNull();
                }
            }
        }
    }

    /**
     * checks if there are any pieces on the board that should be kings and makes them a king
     */
    public static void checkKings() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ( (BOARD[i][j].getTeam() == -1 && i == 9 || BOARD[i][j].getTeam() == 1 && i == 0 )
                        && !BOARD[i][j].isKing()) {
                    BOARD[i][j].makeKing();
                }
            }
        }
    }

}
