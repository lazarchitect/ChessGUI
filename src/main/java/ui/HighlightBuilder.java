package main.java.ui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import main.java.models.Board;
import main.java.models.Piece;
import main.java.util.Enums;
import main.java.util.Utils;

public class HighlightBuilder {

    private HighlightBuilder(){}

    public static void highlightValidMoves(Group uiBoard, Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);

        if(currentPiece == null) return;

        switch(currentPiece.getType()) {
            case Pawn:
                highlightValidPawnMoves(uiBoard, b, row, col);

        }
    }

    private static void highlightValidPawnMoves(Group uiBoard, Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == Enums.PieceColor.Black) {
            if (currentPiece.getRow() == 1) {
                if (b.pieceAt(2, col) == null) {
                    highlight(uiBoard, b, 2, currentPiece.getCol());
                }
                if (b.pieceAt(3, col) == null) {
                    highlight(uiBoard, b, 3, currentPiece.getCol());
                }
            }
            else {
                if (b.pieceAt(currentPiece.getRow() + 1, currentPiece.getCol()) == null) {
                    highlight(uiBoard, b, currentPiece.getRow() + 1, currentPiece.getCol());
                }
            }
        }
    }

    private static void highlight(Group uiBoard, Board b, int row, int col) {
        Group uiRow = (Group) uiBoard.getChildren().get(row);
        Rectangle rect = (Rectangle) uiRow.getChildren().get(col);

        b.tileAt(row, col).setHighlighted(true);

        if(Utils.isDarkTile(row, col)){
            rect.setStroke(Color.web("#FFFFDD"));
            rect.setStrokeWidth(5);
            rect.setStrokeType(StrokeType.INSIDE);
        }
        else {
            rect.setStroke(Color.web("#666633"));
            rect.setStrokeWidth(5);
            rect.setStrokeType(StrokeType.INSIDE);
        }
    }

}
