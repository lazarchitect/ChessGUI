package main.java.models;

import javafx.scene.Group;
import main.java.models.pieces.Pawn;
import main.java.ui.UIBuilder;
import main.java.util.Enums;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public List<List<Tile>> tiles;

    public Board(Group root){
        this.tiles = new ArrayList<>();
        for (int row = 0; row < 8; row++) {

            List<Tile> singleRow = new ArrayList<>();

            for (int col = 0; col < 8; col++) {
                singleRow.add(new Tile());
                UIBuilder.paintTile(root, col, row);
            }

            this.tiles.add(singleRow);

        }

        setInitialPieceLocations(root);



    }

    public void setInitialPieceLocations(Group root){

        UIBuilder.drawPiecesInitial(root);

        tiles.get(0).get(1).setPiece(new Pawn(Enums.Piece.Pawn, Enums.PieceColor.Black));
    }

    public void setPiece(Tile tile, Piece piece) {
        tile.setPiece(piece);
    }

    public void setPiece(int row, int col, Piece piece) {
        tiles.get(row).get(col).setPiece(piece);
    }

    @Override
    public String toString(){
        StringBuilder representation = new StringBuilder();
        for(List<Tile> row: tiles) {
            representation.append(row + "\n");
        }
        return representation.toString();
    }


}
