package main.java.models;

import main.java.util.Enums;

public abstract class Piece {

    public String piece_ID;

    public Tile currentTile;

    public int loc_x;
    public int loc_y;

    public Enums.Piece name; // "bishop", "king", etc
    public Enums.PieceColor color; // "white" or "black"

    public Piece(Enums.Piece name, Enums.PieceColor color) {
        this.name = name;
        this.color = color;
    }

}
