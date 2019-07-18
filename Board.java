class Board {
    Piece[][] board;

    public Board() {
        board = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(1, i, Color.BLACK);
            board[6][i] = new Pawn(6, i, Color.WHITE);
        }
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public boolean movePiece(int oldX, int oldY, int newX, int newY) {
        if (board[oldX][oldY] == null) {
            return false;
        }
        
        boolean canMove = board[oldX][oldY].isValidMove(newX, newY, this);

        if (!canMove) {
            return false;
        }

        board[oldX][oldY].move(newX, newY, this);
        return true;
    }

    public void setNull(int x, int y) {
        board[x][y] = null;
    }

    public void printBoard() {
        System.out.printf("   0  1  2  3  4  5  6  7\n");
        for (int i = 0; i < 8; i++) {
            System.out.printf("%d ", i);
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.printf("[ ]");
                } else {
                    System.out.printf("[%s]", board[i][j].toString());
                }
            }
            System.out.println();
        }
    }
}