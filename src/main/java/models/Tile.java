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
        if(piece == null) return "";
        return piece.color.toString() + " " + piece.name.toString();
    }
}
