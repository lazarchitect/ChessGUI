package main.java.models.pieces;

import main.java.models.Piece;
import main.java.models.Tile;
import main.java.util.Enums.*;

public class Pawn extends Piece {

    public Pawn(PieceType name, PieceColor color, Tile tile) {
       super(name, color, tile);
    }

//    public int[][] validMoves(){
//
//        return new int[][]{{this.loc_x, this.loc_y + 1}};
//
//        // todo all logic
//        //if(this.color.equals("black")){
//            // black always starts at rows 6&7 and moves downward to white
//
//        //}
//    }

}
