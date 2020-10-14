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

        setInitialPieceLocations();

        UIBuilder.drawPiecesInitial(root);

    }

    public void setInitialPieceLocations(){
        tiles.get(0).get(1).piece = new Pawn(Enums.Piece.Pawn, Enums.PieceColor.White);
    }


}
