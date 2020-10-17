package main.java.ui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import main.java.models.Board;
import main.java.models.Piece;
import main.java.models.Tile;
import main.java.util.Constants;
import main.java.util.Enums.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Contains various methods for visualizing UI elements, such as the chessboard
 */
public class UIBuilder {

    private UIBuilder(){}



    public static void drawBoard(Group root, Board b){

        Group uiBoard = new Group();

        for(int row = 0; row < 8; row++){

            Group uiRow = new Group();
            uiBoard.getChildren().add(uiRow);

            for(int col = 0; col < 8; col++){
                drawTile(uiBoard, row, col, b);
            }

        }
        root.getChildren().add(uiBoard);
    }

    public static void drawTile(Group uiBoard, int row, int col, Board b){

        Rectangle rect = new Rectangle(
            (col * Constants.TILE_WIDTH) + Constants.BOARD_X_OFFSET,
            (row * Constants.TILE_HEIGHT) + Constants.BOARD_Y_OFFSET,
            Constants.TILE_WIDTH,
            Constants.TILE_HEIGHT
        );

        // todo: make a method to determine a list of valid tile moves for a given piece
        // todo: make a method to highlight a subset list of tiles
        rect.setOnMouseReleased(event -> highlightValidMoves(uiBoard, b, row, col));

        if(isDarkTile(row, col)){
            rect.setFill(Color.web("#DDDDDD"));
        }
        else{
            rect.setFill(Color.web("#333333"));
        }

        Group uiRow = (Group) uiBoard.getChildren().get(row);
        uiRow.getChildren().add(rect);

        main.java.models.Piece piece = b.pieceAt(row, col);

        if(piece != null){
            drawPiece(uiRow, piece, row, col);
        }
    }

    public static void drawPiece(Group uiBoard, Piece piece, int row, int col) {
        ImageView pieceView = getPieceView(piece);
        if(pieceView == null) return;
        pieceView.setMouseTransparent(true);
        pieceView.setX(Constants.BOARD_X_OFFSET + Constants.PIECE_OFFSET + (Constants.TILE_WIDTH * col));
        pieceView.setY(Constants.BOARD_Y_OFFSET + Constants.PIECE_OFFSET + (Constants.TILE_HEIGHT * row));
        pieceView.setFitHeight(Constants.PIECE_HEIGHT);
        pieceView.setFitWidth(Constants.PIECE_WIDTH);
        uiBoard.getChildren().add(pieceView);
    }

    public static ImageView getPieceView(Piece piece) {
        try {
            String path = "C:/Users/Eddie/Desktop/" + piece.getColor() + piece.getType() + ".png";
            FileInputStream file = new FileInputStream(path);
            Image image = new Image(file);
            return new ImageView(image);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("ERROR: " + piece.getColor() + " " + piece.getType() + " IMAGE NOT FOUND");
            return null;
        }
    }


    private static void highlightValidMoves(Group uiBoard, Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);

        if(currentPiece == null) return;

        switch(currentPiece.getType()) {
            case Pawn:
                highlightValidPawnMoves(uiBoard, b, row, col);

        }
    }

    private static void highlightValidPawnMoves(Group uiBoard, Board b, int row, int col) {
        Piece currentPiece = b.pieceAt(row, col);
        if(currentPiece.getColor() == PieceColor.Black) {
            if (currentPiece.getRow() == 1) {
                if (b.pieceAt(2, col) == null) {
                    highlight(uiBoard, b, 2, currentPiece.getCol());
                }
                if (b.pieceAt(3, col) == null) {
                    highlight(uiBoard, b, 3, currentPiece.getCol());
                }
            }
        }
    }

    private static void highlight(Group uiBoard, Board b, int row, int col) {
        Group uiRow = (Group) uiBoard.getChildren().get(row);
        Rectangle rect = (Rectangle) uiRow.getChildren().get(col);

        if(isDarkTile(row, col)){
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

    private static boolean isDarkTile(int row, int col){
        return (row%2==0 && col%2==0) || (row%2==1 && col%2==1);
    }

}
