package main.java.models;

public class Game {

    Board board;

    Player white;
    Player black;

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
