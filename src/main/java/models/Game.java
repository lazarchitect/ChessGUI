package main.java.models;

import javafx.scene.Group;

public class Game {

    public Board board;

    public Player white;
    public Player black;

    public Game(Group root){
        white = new Player();
        black = new Player();

        board = new Board(root);

    }

    public void movePiece(Tile source, Tile destination){

        if(destination.piece.color.equals("black")){
            black.deadPieces.add(destination.piece);
        }
        else{
            white.deadPieces.add(destination.piece);
        }

        // pass by reference I hope?
        destination.piece = source.piece;
        source.piece = null;
    }

}
