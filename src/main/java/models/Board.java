package main.java.models;

import lombok.Getter;
import lombok.Setter;
import main.java.ui.BoardBuilder;

import java.util.List;

/**
 * Class that represents an 8x8 board state of tiles and pieces.
 * Instantiated via Gson reading JSON, never directly.
 */
public class Board {

    @Getter
    @Setter
    private Piece pieceToMove;

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

    public int move(int destRow, int destCol) {
        int srcRow = pieceToMove.getRow();
        int srcCol = pieceToMove.getCol();

        tileAt(destRow, destCol).setPiece(pieceToMove);
        tileAt(srcRow, srcCol).setPiece(null);
        pieceToMove.setRow(destRow);
        pieceToMove.setCol(destCol);

        return 0;
    }

    public void dehighlight(){
        for(List<Tile> row: tiles){
            for(Tile tile: row){
                tile.setHighlighted(false);
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder representation = new StringBuilder();
        tiles.forEach(row -> representation.append(row + "\n"));
        return representation.toString();
    }


}
