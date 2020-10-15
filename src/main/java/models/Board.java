package main.java.models;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public List<List<Tile>> tiles;

    public Board(Group root){
        this.tiles = new ArrayList<>();
        for (int row = 0; row < 8; row++) {

            List<Tile> singleRow = new ArrayList<>();

            for (int col = 0; col < 8; col++) {
                singleRow.add(new Tile());
            }
            this.tiles.add(singleRow);
        }
    }

    @Override
    public String toString(){
        StringBuilder representation = new StringBuilder();
        for(List<Tile> row: tiles) {
            representation.append(row + "\n");
        }
        return representation.toString();
    }


}
