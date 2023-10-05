package edu.hw1;

public class Task8 {
    @SuppressWarnings("MagicNumber")
    private boolean canBeatUp(int[][] board, int r, int c) {
        return r >= 2 && ((c >= 1 && board[r - 2][c - 1] == 1) || (c <= 6 && board[r - 2][c + 1] == 1));
    }

    @SuppressWarnings("MagicNumber")
    private boolean canBeatDown(int[][] board, int r, int c) {
        return r <= 5 && ((c >= 1 && board[r + 2][c - 1] == 1) || (c <= 6 && board[r + 2][c + 1] == 1));
    }

    @SuppressWarnings("MagicNumber")
    private boolean canBeatLeft(int[][] board, int r, int c) {
        return c >= 2 && ((r >= 1 && board[r - 1][c - 2] == 1) || (r <= 6 && board[r + 1][c - 2] == 1));
    }

    @SuppressWarnings("MagicNumber")
    private boolean canBeatRight(int[][] board, int r, int c) {
        return c <= 5 && ((r >= 1 && board[r - 1][c + 2] == 1) || (r <= 6 && board[r + 1][c + 2] == 1));
    }

    @SuppressWarnings("MagicNumber")
    private boolean canBeat(int[][] board, int r, int c) {
        return canBeatRight(board, r, c)
            || canBeatLeft(board, r, c)
            || canBeatUp(board, r, c)
            || canBeatDown(board, r, c);
    }

    @SuppressWarnings("MagicNumber")
    public boolean knightBoardCapture(int[][] board) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == 1) {
                    if (canBeat(board, r, c)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
