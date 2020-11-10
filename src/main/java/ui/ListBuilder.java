package main.java.ui;

import javafx.scene.Group;
import javafx.scene.control.ListView;

public class ListBuilder {

    public static void buildList(Group root) {
        ListView<String> list = new ListView<>();

        list.getItems().add("sdfsdf");

        root.getChildren().add(list);
    }


}
