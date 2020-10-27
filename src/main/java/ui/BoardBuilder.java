package main.java.ui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.java.models.Board;
import main.java.models.Piece;
import main.java.util.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static main.java.util.Utils.isDarkTile;

/**
 * Contains various methods for visualizing UI elements, such as the chessboard
 */
public class BoardBuilder {

    private BoardBuilder(){}

    public static void drawBoard(Group uiBoard, Board b){

        uiBoard.getChildren().clear();

        for(int row = 0; row < 8; row++){

            Group uiRow = new Group();
            uiBoard.getChildren().add(uiRow);

            for(int col = 0; col < 8; col++){
                drawTile(uiBoard, row, col, b);
            }
        }
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
        rect.setOnMouseReleased(event -> ClickHandler.handleTileClick(uiBoard, b, row, col));

        if(isDarkTile(row, col)){
            rect.setFill(Color.web("#DDDDDD"));
        }
        else{
            rect.setFill(Color.web("#333333"));
        }

        Group uiRow = (Group) uiBoard.getChildren().get(row);

        Group uiTile = new Group();

        uiTile.getChildren().add(rect);

        Piece piece = b.pieceAt(row, col);

        if(piece != null){
            ImageView pieceImageView = createPieceImageView(piece, row, col);
            uiTile.getChildren().add(pieceImageView);
        }

        uiRow.getChildren().add(uiTile);

    }

    public static ImageView createPieceImageView(Piece piece, int row, int col) {
        Image pieceImage = createPieceImage(piece);
        if(pieceImage == null) return null;
        ImageView pieceImageView = new ImageView(pieceImage);
        pieceImageView.setMouseTransparent(true);
        pieceImageView.setX(Constants.BOARD_X_OFFSET + Constants.PIECE_OFFSET + (Constants.TILE_WIDTH * col));
        pieceImageView.setY(Constants.BOARD_Y_OFFSET + Constants.PIECE_OFFSET + (Constants.TILE_HEIGHT * row));
        pieceImageView.setFitHeight(Constants.PIECE_HEIGHT);
        pieceImageView.setFitWidth(Constants.PIECE_WIDTH);
        return pieceImageView;
    }

    public static Image createPieceImage(Piece piece) {
        try {
            String path = "src/main/resources/images/" + piece.getColor() + piece.getType() + ".png";
            FileInputStream file = new FileInputStream(path);
            return new Image(file);
        }
        catch(FileNotFoundException fileNotFoundException){
            System.out.println("ERROR: " + piece.getColor() + " " + piece.getType() + " IMAGE NOT FOUND");
            return null;
        }
    }
}
