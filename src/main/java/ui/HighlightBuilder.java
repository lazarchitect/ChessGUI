package main.java.ui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import main.java.models.Board;
import main.java.models.Piece;
import main.java.util.Enums.*;

public class HighlightBuilder {

    private HighlightBuilder(){}

    public static void highlightValidMoves(Group uiBoard, Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);

        if(currentPiece == null) return;

        switch(currentPiece.getType()) {
            case Pawn:
                highlightValidPawnMoves(uiBoard, b, row, col);
            case Bishop:
                highlightValidBishopMoves(uiBoard, b, row, col);

        }
    }

    private static void highlightValidBishopMoves(Group uiBoard, Board b, int row, int col){
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black){

        }
    }

    private static void highlightValidPawnMoves(Group uiBoard, Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black) {

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
        else { // white pawn
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
    }

    private static void highlight(Group uiBoard, Board b, int row, int col) {

        b.tileAt(row, col).setHighlighted(true);

        Rectangle rect = (Rectangle) ((Group) ((Group) uiBoard.getChildren().get(row)).getChildren().get(col)).getChildren().get(0);
        rect.setStroke(Color.web("#888833"));
        rect.setStrokeWidth(5);
        rect.setStrokeType(StrokeType.INSIDE);

    }

}
