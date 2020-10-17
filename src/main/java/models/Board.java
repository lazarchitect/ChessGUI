package main.java.models;

import java.util.List;

public class Board {

    private List<List<Tile>> tiles;

    public List<Tile> rowAt(int row) {
        return this.tiles.get(row);
    }

    public Tile tileAt(int row, int col) {
        return this.tiles.get(row).get(col);
    }

    public Piece pieceAt(int row, int col) {
        return this.tiles.get(row).get(col).getPiece();
    }

    @Override
    public String toString(){
        StringBuilder representation = new StringBuilder();
        tiles.forEach(row -> representation.append(row + "\n"));
        return representation.toString();
    }


}
