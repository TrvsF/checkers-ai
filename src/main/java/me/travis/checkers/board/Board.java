package me.travis.checkers.board;

/*
 * class for holding all the information on the board, including the board itself
 * this doesn't really *have* to be a class, but its a lot nicer to have the board object in its own space
 */
public class Board {

    public static Man[][] BOARD = createBoard();

    /*
     * create the initial state of the board
     * populates a 2d array of 'Man' objects and assigns them their correct starting positions
     */
    private static Man[][] createBoard() {
        Man[][] board = new Man[8][];
        for (int i = 0; i < 8; i++) {
            board[i] = new Man[8];
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    if (i < 3) {
                        board[i][j] = new Man(-1);
                    } else if (i > 4) {
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

    /*
     * prints out the board to the console
     * used for debugging (hence the name)
     */
    public static void printDebugBoard() {
        for (Man[] men : BOARD) {
            for (Man man : men) {
                System.out.print(man.getTeam());
            }
            System.out.print("\n");
        }
    }

}
