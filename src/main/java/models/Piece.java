package main.java.models;

import main.java.util.Enums.PieceColor;
import main.java.util.Enums.PieceType;

public class Piece {

    private Tile currentTile;

    private int row;
    private int col;

    private PieceType type;
    private PieceColor color;


    public Piece(PieceType type, PieceColor color, Tile tile) {
        this.setCurrentTile(tile);
        this.setType(type);
        this.setColor(color);
    }

    @Override
    public String toString() {
        return getColor() + " " + getType();
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }
}
