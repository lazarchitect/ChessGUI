package main.java.models;

import javafx.scene.Group;
import main.java.ui.UIBuilder;
import main.java.util.Enums;

public class Game {

    private Board board;

    private Player white;
    private Player black;

    public Game(Group root, Board b) {
        setWhite(new Player());
        setBlack(new Player());
        setBoard(b);
        UIBuilder.drawBoard(root);
        b.setInitialPieceLocations(root);
    }

    public Game(Group root){
        setWhite(new Player());
        setBlack(new Player());

        setBoard(new Board(root));

    }

    public void movePiece(Tile source, Tile destination){

        if(destination.getPiece().color.equals(Enums.PieceColor.Black)){
            getBlack().deadPieces.add(destination.getPiece());
        }
        else{
            getWhite().deadPieces.add(destination.getPiece());
        }

        // pass by reference I hope?
        destination.setPiece(source.getPiece());
        source.setPiece(null);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWhite() {
        return white;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public Player getBlack() {
        return black;
    }

    public void setBlack(Player black) {
        this.black = black;
    }
}
