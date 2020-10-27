package main.java.ui;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import main.java.models.Board;
import main.java.util.Enums.*;

public class ClickHandler {

    public static void handleTileClick(Group uiBoard, Board b, int row, int col){
        if(b.tileAt(row, col).isHighlighted()){
            int moveResult = b.move(row, col);
            if(moveResult == 0){
                BoardBuilder.drawBoard(uiBoard, b);
                b.setPieceToMove(null);
                b.dehighlight();
            }

        }
        else{
            b.dehighlight();
            BoardBuilder.drawBoard(uiBoard, b);
            HighlightBuilder.highlightValidMoves(uiBoard, b, row, col);
            b.setPieceToMove(b.pieceAt(row, col));
        }
    }

}
