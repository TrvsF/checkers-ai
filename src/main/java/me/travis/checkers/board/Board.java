package me.travis.checkers.board;

public class Board {

    private final Man[][] board;

    public Board() {
        this.board = createBoard();
    }

    public Man[][] getBoard() {
        return this.board;
    }

    private Man[][] createBoard() {
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

    public void printDebugBoard() {
        for (Man[] men : this.board) {
            for (Man man : men) {
                System.out.print(man.getId());
            }
            System.out.print("\n");
        }
    }

}
