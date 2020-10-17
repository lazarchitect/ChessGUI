package main.java.ui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.java.models.Board;
import main.java.models.Piece;
import main.java.util.Constants;
import main.java.util.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Contains various methods for visualizing UI elements, such as the chessboard
 */
public class BoardBuilder {

    private BoardBuilder(){}

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
        rect.setOnMouseReleased(event -> HighlightBuilder.highlightValidMoves(uiBoard, b, row, col));

        if(Utils.isDarkTile(row, col)){
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






}
