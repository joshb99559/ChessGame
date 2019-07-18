abstract class Piece {
    int x, y;
    Color color;

    public Piece(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public abstract boolean isValidMove(int x, int y, Board board);  //IsValidMove determines whether or not the piece can make the move requested
    public abstract boolean move(int x, int y, Board board);    //Move actually moves the piece on the board after checking that the move is legal
    public abstract String toString();
}

class Pawn extends Piece {
    public Pawn(int x, int y, Color color) {
        super(x, y, color);
    }

    boolean hasMoved;

    /**
     * IsValidMove for the Pawn Piece
     * 
     * The Pawn can move forward by one space at any time unless the space ahead of it is occupied.
     * If the Pawn has never moved, it can move two spaces.
     * If the Pawn has an enemy piece ahead and diagonal to it, it can capture that piece with a diagonal move.
     */
    public boolean isValidMove(int x, int y, Board board) {
        Piece[][] gameBoard = board.getBoard();

        if (x < 0 || y < 0 || x > 7 || y > 7) {    //In bounds of board
            return false;
        }

        if (this.color == Color.WHITE) {                        //WHITE MOVE
            if (gameBoard[x][y] != null) {  //Space Occupied
                if (gameBoard[x][y].getColor() == Color.WHITE) {
                    return false;           //Space Occupied by ally
                } else {
                    if ((x == this.x - 1) && (y == this.y + 1 || y == this.y - 1)) {    //Capture
                        hasMoved = true;
                        return true;
                    }
                }
            } else {                        //Space Unoccupied
                if (!this.hasMoved) {        //Option for 2 space move
                    if (y == this.y && (x == this.x - 1 || x == this.x - 2)) {
                        hasMoved = true;
                        return true;
                    }
                } else {
                    return (y == this.y && x == this.x - 1);
                }
            }
        } else {                                                //BLACK MOVE
            if (gameBoard[x][y] != null) {  //Space Occupied
                if (gameBoard[x][y].getColor() == Color.BLACK) {
                    return false;           //Space Occupied by ally
                } else {
                    if ((x == this.x + 1) && (y == this.y + 1 || y == this.y - 1)) {    //Capture
                        return true;
                    }
                }
            } else {                        //Space Unoccupied
                if (!this.hasMoved) {        //Option for 2 space move
                    if (y == this.y && (x == this.x - 1 || x == this.x - 2)) {
                        hasMoved = true;
                        return true;
                    }
                } else {
                    return (y == this.y && x == this.x + 1);
                }
            }
        }
        return false;
    }

    public boolean move(int x, int y, Board board) {
        boolean captured = false;
        if (board.getPiece(x, y) != null && board.getPiece(x, y).getColor() != this.color) {
            captured = true;
        }

        board.setNull(this.x, this.y);

        this.x = x;
        this.y = y;
        board.setPiece(x, y, this);

        return captured;
    }

    public String toString() {
        if (this.color == Color.WHITE)
            return "W";
        else 
            return "B";
    }
}