package main.java.models.pieces;

import main.java.models.Piece;
import main.java.util.Enums;

public class Pawn extends Piece {

    public Pawn(Enums.Piece name, Enums.PieceColor color) {
       super(name, color);
    }

    public int[][] validMoves(){

        return new int[][]{{this.loc_x, this.loc_y + 1}};

        // todo all logic
        //if(this.color.equals("black")){
            // black always starts at rows 6&7 and moves downward to white

        //}
    }

}
