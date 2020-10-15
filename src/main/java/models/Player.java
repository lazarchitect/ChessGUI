package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Player {

    String name;

    List<Piece> deadPieces;

    public Player(){
        deadPieces = new ArrayList<>();
    }

}
