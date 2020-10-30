package main.java.logic;

import main.java.util.Enums.*;


import static main.java.util.Utils.outOfBounds;

public class MoveLogic {

    private MoveLogic(){}

    private static final int[][] knightOffsets = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
    private static final int[][] bishopOffsets = {{1,1},{1,-1},{-1,1},{-1,-1}};
    private static final int[][] rookOffsets = {{0,1},{0,-1},{1,0},{-1,0}};
    private static final int[][] royalOffsets = {{1,1},{1,-1},{-1,1},{-1,-1},{0,1},{0,-1},{1,0},{-1,0}};

    private static void scan(CoordinateList coords, Board b, int row, int col, int rowOffset, int colOffset, PieceColor pieceColor) {

        if(outOfBounds(row + rowOffset, col + colOffset) ) {
            return;
        }

        if(b.hasPieceAt(row + rowOffset, col + colOffset)){
            if(b.pieceAt(row + rowOffset, col + colOffset).getColor() != pieceColor) {
                coords.add(row + rowOffset, col + colOffset);
            }
            return;
        }

        coords.add(row + rowOffset, col + colOffset);
        scan(coords, b, row + rowOffset, col + colOffset, rowOffset, colOffset, pieceColor);


    }

    public static CoordinateList validPawnMoves(Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black) {
            return validBlackPawnMoves(b, row, col);
        }
        else { // white pawn
            return validWhitePawnMoves(b, row, col);
        }
    }

    private static CoordinateList validBlackPawnMoves(Board b, int row, int col) {

        CoordinateList retval = new CoordinateList();

        if(row == 7) return retval;

        if(!b.hasPieceAt(row + 1, col)){
            retval.add(row + 1, col);
        }
        if(row == 1 && !b.hasPieceAt(row + 1, col) && !b.hasPieceAt(row + 2, col)){
            retval.add(row + 2, col);
        }
        if(col != 7 && b.hasWhitePieceAt(row + 1, col + 1)){
            retval.add(row + 1, col + 1);
        }
        if(col != 0 && b.hasWhitePieceAt(row + 1, col - 1)){
            retval.add(row + 1, col - 1);
        }
        return retval;
    }

    public static CoordinateList validWhitePawnMoves(Board b, int row, int col) {

        CoordinateList retval = new CoordinateList();

        if(row == 0) return retval;

        if(!b.hasPieceAt(row - 1, col)){
            retval.add(row - 1, col);
        }
        if(row == 6 && !b.hasPieceAt(row - 1, col) && !b.hasPieceAt(row - 2, col)){
            retval.add(row - 2, col);
        }
        if(col != 7 && b.hasBlackPieceAt(row - 1, col + 1)){
            retval.add(row - 1, col + 1);
        }
        if(col != 0 && b.hasBlackPieceAt(row - 1, col - 1)){
            retval.add(row - 1, col - 1);
        }
        return retval;
    }


    public static CoordinateList validBishopMoves(Board b, int row, int col){
        PieceColor pieceColor = b.pieceAt(row, col).getColor();

        CoordinateList validMoves = new CoordinateList();

        for(int[] bishopOffset: bishopOffsets) {
            scan(validMoves, b, row, col, bishopOffset[0], bishopOffset[1], pieceColor);
        }
        return validMoves;
    }



    public static CoordinateList validKnightMoves(Board b, int row, int col){

        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black){
            return validBlackKnightMoves(b, row, col);

        }
        else { // white Knight
            return validWhiteKnightMoves( b, row, col);
        }
    }

    private static CoordinateList validBlackKnightMoves(Board b, int row, int col){
        CoordinateList retval = new CoordinateList();
        for(int[] xyOffset: knightOffsets){
            int destRow = row + xyOffset[0];
            int destCol = col + xyOffset[1];
            if(outOfBounds(destRow, destCol)) continue;
            if(b.tileAt(row + xyOffset[0], col + xyOffset[1]).isEmpty() || b.hasWhitePieceAt(row + xyOffset[0], col + xyOffset[1])) {
                retval.add(destRow, destCol);
            }
        }
        return retval;
    }


    private static CoordinateList validWhiteKnightMoves(Board b, int row, int col){
        CoordinateList retval = new CoordinateList();
        for(int[] coord: knightOffsets){
            int destRow = row + coord[0];
            int destCol = col + coord[1];
            if(outOfBounds(destRow, destCol)) continue;
            if(b.tileAt(row + coord[0], col + coord[1]).isEmpty() || b.hasBlackPieceAt(row + coord[0], col + coord[1])) {
                retval.add(destRow, destCol);
            }
        }
        return retval;
    }

    public static CoordinateList validQueenMoves(Board b, int row, int col){
        PieceColor pieceColor = b.pieceAt(row, col).getColor();

        CoordinateList validMoves = new CoordinateList();

        for(int[] queenOffset: royalOffsets) {
            scan(validMoves, b, row, col, queenOffset[0], queenOffset[1], pieceColor);
        }
        return validMoves;
    }

    public static CoordinateList validKingMoves(Board b, int row, int col){
        CoordinateList retval = new CoordinateList();
        PieceColor pieceColor = b.pieceAt(row, col).getColor();

        for (int[] kingOffset : royalOffsets) {
            int destRow = row + kingOffset[0];
            int destCol = col + kingOffset[1];

            if (outOfBounds(destRow, destCol)) continue;

            if ((b.tileAt(destRow, destCol).isEmpty() || b.pieceAt(destRow, destCol).getColor() != pieceColor)) {
                // TODO make sure this dest tile isnt dangerous for our dear leader
                retval.add(row + kingOffset[0], col + kingOffset[1]);
            }
        }


        return retval;
    }


    public static CoordinateList validRookMoves(Board b, int row, int col){
        PieceColor pieceColor = b.pieceAt(row, col).getColor();

        CoordinateList validMoves = new CoordinateList();

        for(int[] rookOffset: rookOffsets) {
            scan(validMoves, b, row, col, rookOffset[0], rookOffset[1], pieceColor);
        }
        return validMoves;
    }

}
