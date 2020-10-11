package main.java.models.pieces;

import main.java.models.Piece;

public class Pawn extends Piece {

    public int[][] validMoves(){

        return new int[][]{{this.loc_x, this.loc_y + 1}};

        // todo all logic
        //if(this.color.equals("black")){
            // black always starts at rows 6&7 and moves downward to white

        //}
    }

}
