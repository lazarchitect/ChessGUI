package main.java.models;

public class Tile {

    private Piece piece;

    public Tile(){
        this.piece = null;
    }

    public Tile(Piece piece){
        this.piece = piece;
    }

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
