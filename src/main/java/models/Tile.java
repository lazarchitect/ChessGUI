package main.java.models;

/**
 * A single tile on a chessboard. Its only field is a reference to a chess piece object.
 * No constructors: instead, this class gets instantiated from JSON by Gson.
 */
public class Tile {

    private Piece piece;

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return piece != null ? piece.toString() : null;
    }
}
