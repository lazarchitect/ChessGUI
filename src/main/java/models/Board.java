package main.java.models;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public List<List<Tile>> tiles;

    @Override
    public String toString(){
        StringBuilder representation = new StringBuilder();
        for(List<Tile> row: tiles) {
            representation.append(row + "\n");
        }
        return representation.toString();
    }


}
