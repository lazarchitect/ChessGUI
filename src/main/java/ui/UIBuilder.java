package main.java.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.java.util.Constants;
import main.java.util.Enums.Piece;
import main.java.util.Enums.PieceColor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Contains various methods for visualizing UI elements, such as the chessboard
 */
public class UIBuilder {

    public static void drawPiecesInitial(Pane root){

        drawPiece(root, PieceColor.White, Piece.King, 3, 0);
        drawPiece(root, PieceColor.White, Piece.Queen, 4, 0);
        drawPiece(root, PieceColor.White, Piece.Rook, 7, 0);
        drawPiece(root, PieceColor.White, Piece.Rook, 0, 0);
        drawPiece(root, PieceColor.White, Piece.Bishop, 2, 0);
        drawPiece(root, PieceColor.White, Piece.Bishop, 5, 0);
        drawPiece(root, PieceColor.White, Piece.Knight, 1, 0);
        drawPiece(root, PieceColor.White, Piece.Knight, 6, 0);

        drawPiece(root, PieceColor.Black, Piece.King, 3, 7);
        drawPiece(root, PieceColor.Black, Piece.Queen, 4, 7);
        drawPiece(root, PieceColor.Black, Piece.Rook, 7, 7);
        drawPiece(root, PieceColor.Black, Piece.Rook, 0, 7);
        drawPiece(root, PieceColor.Black, Piece.Bishop, 2, 7);
        drawPiece(root, PieceColor.Black, Piece.Bishop, 5, 7);
        drawPiece(root, PieceColor.Black, Piece.Knight, 1, 7);
        drawPiece(root, PieceColor.Black, Piece.Knight, 6, 7);
        drawPawnsInitial(root);
    }

    public static void drawPawnsInitial(Pane root){

        for(int col = 0; col < 8; col++) {
            drawPiece(root, PieceColor.White, Piece.Pawn, col, 1);
        }

        for(int col = 0; col < 8; col++) {
            drawPiece(root, PieceColor.Black, Piece.Pawn, col, 6);
        }
    }

    public static void drawPiece(Pane root, PieceColor color, Piece piece, int col, int row) {
        ImageView pieceView = getPieceView(color, piece);
        if(pieceView == null) return;
        pieceView.setX(Constants.BOARD_X_OFFSET + Constants.PIECE_OFFSET + (Constants.TILE_WIDTH * col));
        pieceView.setY(Constants.BOARD_Y_OFFSET + Constants.PIECE_OFFSET + (Constants.TILE_HEIGHT * row));
        pieceView.setFitHeight(Constants.PIECE_HEIGHT);
        pieceView.setFitWidth(Constants.PIECE_WIDTH);
        root.getChildren().add(pieceView);
    }

    public static ImageView getPieceView(PieceColor color, Piece piece) {
        try {
            String path = "C:/Users/Eddie/Desktop/" + color + piece + ".png";
            FileInputStream file = new FileInputStream(path);
            Image image = new Image(file);
            return new ImageView(image);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("ERROR: " + color + " " + piece + " IMAGE NOT FOUND");
            return null;
        }
    }

    public static void drawChessterText(Pane root){
        Text text = new Text();
        text.setText("Hi, I'm Chesster!");
        text.setX(Constants.CHESSTER_X_OFFSET);
        text.setY(Constants.CHESSTER_Y_OFFSET);
        root.getChildren().add(text);
    }

    public static void drawChesster(Pane root){
        try {
            FileInputStream file = new FileInputStream("C:/Users/Eddie/Desktop/Chesster.png");
            Image image = new Image(file);
            ImageView chesster = new ImageView(image);
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

    /**
     * Creates a canvas and uses its GraphicsContext to draw the board, tile by tile.
     * @param root of the FX page, used to attach the board as a child after creation
     */
    public static void drawBoard(Pane root){
        final Canvas chessboardCanvas = new Canvas(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        GraphicsContext gc = chessboardCanvas.getGraphicsContext2D();
        paintBoard(gc);
        root.getChildren().add(chessboardCanvas);
    }

    /**
     * creates the FX chessboard, row by row.
     * @param gc the graphics context to paint on.
     */
    private static void paintBoard(GraphicsContext gc){
        for(int y = 0; y < 8; y++){
            paintRow(y, gc);
        }
    }

    /**
     * creates a single row of the FX chessboard, tile by tile.
     * @param gc the graphics context to paint on.
     */
    private static void paintRow(int y, GraphicsContext gc){
        for(int x = 0; x <8; x++){
            paintTile(y, x, gc);
        }
    }

    /**
     * creates a single tile of the FX chessboard, by painting a rectangle with some given dimensions and location.
     * @param gc the graphics context to paint on.
     */
    private static void paintTile(int y, int x, GraphicsContext gc){
        if((y%2==0 && x%2==0) || (y%2==1 && x%2==1)){
            gc.setFill(Color.web("#DDDDDD"));
        }
        else{
            gc.setFill(Color.web("#333333"));
        }
        gc.fillRect(
                (x * Constants.TILE_WIDTH) + Constants.BOARD_X_OFFSET,
                (y * Constants.TILE_HEIGHT) + Constants.BOARD_Y_OFFSET,
                Constants.TILE_WIDTH,
                Constants.TILE_HEIGHT);
    }

}
