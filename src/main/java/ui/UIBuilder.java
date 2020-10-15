package main.java.ui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.java.models.Board;
import main.java.models.Piece;
import main.java.util.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Contains various methods for visualizing UI elements, such as the chessboard
 */
public class UIBuilder {

    private UIBuilder(){}

    public static void drawChessterText(Group root){
        Text text = new Text();
        text.setText("Hi, I'm Chesster!");
        text.setX(Constants.CHESSTER_X_OFFSET);
        text.setY(Constants.CHESSTER_Y_OFFSET);
        root.getChildren().add(text);
    }

    public static void drawChesster(Group root){
        try {
            FileInputStream file = new FileInputStream("C:/Users/Eddie/Desktop/Chesster.png");
            Image image = new Image(file);
            ImageView chesster = new ImageView(image);
            chesster.setOnMouseReleased(event -> System.out.println("hi"));
            chesster.setX(Constants.CHESSTER_X_OFFSET);
            chesster.setY(Constants.CHESSTER_Y_OFFSET);
            chesster.setFitHeight(Constants.CHESSTER_HEIGHT);
            chesster.setFitWidth(Constants.CHESSTER_WIDTH);
            root.getChildren().add(chesster);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("ERROR: CHESSTER IMAGE NOT FOUND");
        }
    }

    public static void drawBoard(Group root, Board b){

        Group uiBoard = new Group();

        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                paintTile(uiBoard, row, col, b);
            }
        }
        root.getChildren().add(uiBoard);
    }

    public static void paintTile(Group uiBoard, int row, int col, Board b){

        Rectangle rect = new Rectangle(
            (col * Constants.TILE_WIDTH) + Constants.BOARD_X_OFFSET,
            (row * Constants.TILE_HEIGHT) + Constants.BOARD_Y_OFFSET,
            Constants.TILE_WIDTH,
            Constants.TILE_HEIGHT
        );

        rect.setOnMouseReleased(event -> b.tiles.get(row).get(col).getPiece().);

        if((row%2==0 && col%2==0) || (row%2==1 && col%2==1)){
            rect.setFill(Color.web("#DDDDDD"));
        }
        else{
            rect.setFill(Color.web("#333333"));
        }

        uiBoard.getChildren().add(rect);

        main.java.models.Piece piece = b.tiles.get(row).get(col).getPiece();

        if(piece != null){
            drawPiece(uiBoard, piece, row, col);
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
