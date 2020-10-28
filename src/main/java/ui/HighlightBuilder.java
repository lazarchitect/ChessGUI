package main.java.ui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import main.java.models.Board;
import main.java.models.Piece;
import main.java.util.Enums.*;

import static main.java.util.Utils.outOfBounds;

public class HighlightBuilder {

    private static final int[][] knightMoves = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};

    private HighlightBuilder(){}

    public static void highlightValidMoves(Group uiBoard, Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);

        switch (currentPiece.getType()) {
            case Pawn -> highlightValidPawnMoves(uiBoard, b, row, col);
            case Bishop -> highlightValidBishopMoves(uiBoard, b, row, col);
            case Knight -> highlightValidKnightMoves(uiBoard, b, row, col);
            case Queen -> highlightValidQueenMoves(uiBoard, b, row, col);
            case King -> highlightValidKingMoves(uiBoard, b, row, col);
            case Rook -> highlightValidRookMoves(uiBoard, b, row, col);
        }
    }

    private static void highlightValidPawnMoves(Group uiBoard, Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black) {
            highlightValidBlackPawnMoves(uiBoard, b, row, col);
        }
        else { // white pawn
            highlightValidWhitePawnMoves(uiBoard, b, row, col);
        }
    }

    private static void highlightValidBlackPawnMoves(Group uiBoard, Board b, int row, int col) {
        if(row == 7) return;

        if(!b.hasPieceAt(row + 1, col)){
            highlight(uiBoard, b, row + 1, col);
        }
        if(row == 1 && !b.hasPieceAt(row + 1, col) && !b.hasPieceAt(row + 2, col)){
            highlight(uiBoard, b, row + 2, col);
        }
        if(col != 7 && b.hasWhitePieceAt(row + 1, col + 1)){
            highlight(uiBoard, b, row + 1, col + 1);
        }
        if(col != 0 && b.hasWhitePieceAt(row + 1, col - 1)){
            highlight(uiBoard, b, row + 1, col - 1);
        }
    }

    private static void highlightValidWhitePawnMoves(Group uiBoard, Board b, int row, int col) {
        if(row == 0) return;

        if(!b.hasPieceAt(row - 1, col)){
            highlight(uiBoard, b, row - 1, col);
        }
        if(row == 6 && !b.hasPieceAt(row - 1, col) && !b.hasPieceAt(row - 2, col)){
            highlight(uiBoard, b, row - 2, col);
        }
        if(col != 7 && b.hasBlackPieceAt(row - 1, col + 1)){
            highlight(uiBoard, b, row - 1, col + 1);
        }
        if(col != 0 && b.hasBlackPieceAt(row - 1, col - 1)){
            highlight(uiBoard, b, row - 1, col - 1);
        }
    }


    private static void highlightValidBishopMoves(Group uiBoard, Board b, int row, int col){
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black){
            highlightValidBlackBishopMoves(uiBoard, b, row, col);
        }
        else { // white Bishop
            highlightValidWhiteBishopMoves(uiBoard, b, row, col);
        }
    }

    private static void highlightValidBlackBishopMoves(Group uiBoard, Board b, int row, int col) {
        int i = 0;
        while(true){
            i++;
            if(outOfBounds(row + i, col + i) || b.hasPieceAt(row + i, col + i)){
                if(!outOfBounds(row+i, col+i) && b.hasWhitePieceAt(row + i, col + i)){
                    highlight(uiBoard, b, row + i, col + i);
                }
                break;
            }
            else {
                highlight(uiBoard, b, row + i, col + i);
            }
        }

    }

    private static void highlightValidWhiteBishopMoves(Group uiBoard, Board b, int row, int col) {

    }

    private static void highlightValidKnightMoves(Group uiBoard, Board b, int row, int col){

        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black){
            highlightValidBlackKnightMoves(uiBoard, b, row, col);

        }
        else { // white Knight
            highlightValidWhiteKnightMoves(uiBoard, b, row, col);
        }
    }

    private static void highlightValidBlackKnightMoves(Group uiBoard, Board b, int row, int col){
        for(int[] coord: knightMoves){
            int destRow = row + coord[0];
            int destCol = col + coord[1];
            if(outOfBounds(destRow, destCol)) continue;
            if(b.tileAt(row + coord[0], col + coord[1]).isEmpty() || b.hasWhitePieceAt(row + coord[0], col + coord[1])) {
                highlight(uiBoard, b, destRow, destCol);
            }
        }
    }


    private static void highlightValidWhiteKnightMoves(Group uiBoard, Board b, int row, int col){
        for(int[] coord: knightMoves){
            int destRow = row + coord[0];
            int destCol = col + coord[1];
            if(outOfBounds(destRow, destCol)) continue;
            if(b.tileAt(row + coord[0], col + coord[1]).isEmpty() || b.hasBlackPieceAt(row + coord[0], col + coord[1])) {
                highlight(uiBoard, b, destRow, destCol);
            }
        }
    }



    private static void highlightValidQueenMoves(Group uiBoard, Board b, int row, int col){
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black){
            // todo Black queen logic
        }
        else { // white Bishop
            // todo white queen logic
        }
    }

    private static void highlightValidKingMoves(Group uiBoard, Board b, int row, int col){
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black){
            // todo Black king logic
        }
        else { // white Bishop
            // todo white king logic
        }
    }


    private static void highlightValidRookMoves(Group uiBoard, Board b, int row, int col){
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black){
            // todo Black rook logic
        }
        else { // white Bishop
            // todo white rook logic
        }
    }

    private static void highlight(Group uiBoard, Board b, int row, int col) {

        b.tileAt(row, col).setHighlighted(true);

        Rectangle rect = (Rectangle) ((Group) ((Group) uiBoard.getChildren().get(row)).getChildren().get(col)).getChildren().get(0);
        rect.setStroke(Color.web("#888833"));
        rect.setStrokeWidth(5);
        rect.setStrokeType(StrokeType.INSIDE);

    }

}
