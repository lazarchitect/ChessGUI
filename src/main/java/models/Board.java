package main.java.models;

import java.util.List;

public class Board {

    public List<List<Tile>> tiles;

    @Override
    public String toString(){
        StringBuilder representation = new StringBuilder();
        tiles.forEach(row -> representation.append(row + "\n"));
        return representation.toString();
    }


}
