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
        else {
            if (currentPiece.getRow() == 6) {
                if (b.pieceAt(5, col) == null) {
                    highlight(uiBoard, b, 5, currentPiece.getCol());
                }
                if (b.pieceAt(4, col) == null) {
                    highlight(uiBoard, b, 4, currentPiece.getCol());
                }
            }
            else {
                if (b.pieceAt(currentPiece.getRow() - 1, currentPiece.getCol()) == null) {
                    highlight(uiBoard, b, currentPiece.getRow() - 1, currentPiece.getCol());
                }
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
