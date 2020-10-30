package main.java.logic;

import main.java.util.Enums;

import java.util.ArrayList;
import java.util.List;

import static main.java.util.Utils.outOfBounds;

public class MoveLogic {

    private MoveLogic(){}

    private static final int[][] knightOffsets = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
    private static final int[][] bishopOffsets = {{1,1},{1,-1},{-1,1},{-1,-1}};
    private static final int[][] rookOffsets = {{0,1},{0,-1},{1,0},{-1,0}};
    private static final int[][] queenOffsets = {{1,1},{1,-1},{-1,1},{-1,-1},{0,1},{0,-1},{1,0},{-1,0}};

    private static void scan(List<Coordinate> coords, Board b, int row, int col, int rowOffset, int colOffset, Enums.PieceColor pieceColor) {

        if(outOfBounds(row + rowOffset, col + colOffset) ) {
            return;
        }

        if(b.hasPieceAt(row + rowOffset, col + colOffset)){
            if(b.pieceAt(row + rowOffset, col + colOffset).getColor() != pieceColor) {
                coords.add(new Coordinate(row + rowOffset, col + colOffset));
            }
            return;
        }

        coords.add(new Coordinate(row + rowOffset, col + colOffset));
        scan(coords, b, row + rowOffset, col + colOffset, rowOffset, colOffset, pieceColor);


    }

    public static List<Coordinate> validPawnMoves(Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == Enums.PieceColor.Black) {
            return validBlackPawnMoves(b, row, col);
        }
        else { // white pawn
            return validWhitePawnMoves(b, row, col);
        }
    }

    private static List<Coordinate> validBlackPawnMoves(Board b, int row, int col) {

        List<Coordinate> retval = new ArrayList<>();

        if(row == 7) return retval;

        if(!b.hasPieceAt(row + 1, col)){
            retval.add(new Coordinate(row + 1, col));
        }
        if(row == 1 && !b.hasPieceAt(row + 1, col) && !b.hasPieceAt(row + 2, col)){
            retval.add(new Coordinate(row + 2, col));
        }
        if(col != 7 && b.hasWhitePieceAt(row + 1, col + 1)){
            retval.add(new Coordinate(row + 1, col + 1));
        }
        if(col != 0 && b.hasWhitePieceAt(row + 1, col - 1)){
            retval.add(new Coordinate(row + 1, col - 1));
        }
        return retval;
    }

    public static List<Coordinate> validWhitePawnMoves(Board b, int row, int col) {

        List<Coordinate> retval = new ArrayList<>();

        if(row == 0) return retval;

        if(!b.hasPieceAt(row - 1, col)){
            retval.add(new Coordinate(row - 1, col));
        }
        if(row == 6 && !b.hasPieceAt(row - 1, col) && !b.hasPieceAt(row - 2, col)){
            retval.add(new Coordinate(row - 2, col));
        }
        if(col != 7 && b.hasBlackPieceAt(row - 1, col + 1)){
            retval.add(new Coordinate(row - 1, col + 1));
        }
        if(col != 0 && b.hasBlackPieceAt(row - 1, col - 1)){
            retval.add(new Coordinate(row - 1, col - 1));
        }
        return retval;
    }


    public static List<Coordinate> validBishopMoves(Board b, int row, int col){
        Enums.PieceColor pieceColor = b.pieceAt(row, col).getColor();

        List<Coordinate> validMoves = new ArrayList<>();

        for(int[] bishopOffset: bishopOffsets) {
            scan(validMoves, b, row, col, bishopOffset[0], bishopOffset[1], pieceColor);
        }
        return validMoves;
    }



    public static List<Coordinate> validKnightMoves(Board b, int row, int col){

        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == Enums.PieceColor.Black){
            return validBlackKnightMoves(b, row, col);

        }
        else { // white Knight
            return validWhiteKnightMoves( b, row, col);
        }
    }

    private static List<Coordinate> validBlackKnightMoves(Board b, int row, int col){
        List<Coordinate> retval = new ArrayList<>();
        for(int[] xyOffset: knightOffsets){
            int destRow = row + xyOffset[0];
            int destCol = col + xyOffset[1];
            if(outOfBounds(destRow, destCol)) continue;
            if(b.tileAt(row + xyOffset[0], col + xyOffset[1]).isEmpty() || b.hasWhitePieceAt(row + xyOffset[0], col + xyOffset[1])) {
                retval.add(new Coordinate(destRow, destCol));
            }
        }
        return retval;
    }


    private static List<Coordinate> validWhiteKnightMoves(Board b, int row, int col){
        List<Coordinate> retval = new ArrayList<>();
        for(int[] coord: knightOffsets){
            int destRow = row + coord[0];
            int destCol = col + coord[1];
            if(outOfBounds(destRow, destCol)) continue;
            if(b.tileAt(row + coord[0], col + coord[1]).isEmpty() || b.hasBlackPieceAt(row + coord[0], col + coord[1])) {
                retval.add(new Coordinate(destRow, destCol));
            }
        }
        return retval;
    }

    public static List<Coordinate> validQueenMoves(Board b, int row, int col){
        Enums.PieceColor pieceColor = b.pieceAt(row, col).getColor();

        List<Coordinate> validMoves = new ArrayList<>();

        for(int[] queenOffset: queenOffsets) {
            scan(validMoves, b, row, col, queenOffset[0], queenOffset[1], pieceColor);
        }
        return validMoves;
    }

    public static List<Coordinate> validKingMoves(Board b, int row, int col){
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == Enums.PieceColor.Black){
            //return validBlackKingMoves(b, row, col);
        }
        else { // white King
            //return validWhiteKingMoves(b, row, col);
        }
        return null;
    }


    public static List<Coordinate> validRookMoves(Board b, int row, int col){
        Enums.PieceColor pieceColor = b.pieceAt(row, col).getColor();

        List<Coordinate> validMoves = new ArrayList<>();

        for(int[] rookOffset: rookOffsets) {
            scan(validMoves, b, row, col, rookOffset[0], rookOffset[1], pieceColor);
        }
        return validMoves;
    }

}
