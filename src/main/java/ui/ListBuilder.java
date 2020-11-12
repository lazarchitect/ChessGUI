package main.java.ui;

import javafx.scene.Group;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import main.java.logic.Tile;

public class ListBuilder {


    private static class ListItem {
        String opponent;
        String gamemode;

        public ListItem(String opponent, String gamemode){
            this.opponent = opponent;
            this.gamemode = gamemode;
        }

        public String toString() {
            return this.gamemode + " vs. " + this.opponent;
        }
    }

    public static void buildList(Group root) {
        ListView<ListItem> list = new ListView<>();

        list.getItems().add(new ListItem("Cory", "Correspondence"));

        root.getChildren().add(list);
    }


}
