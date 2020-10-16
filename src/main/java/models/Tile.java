package main.java.models;

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
