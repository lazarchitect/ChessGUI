package main.java.ui;

import javafx.scene.Group;
import lombok.Data;

import java.util.List;

@Data
public class UIRow extends Group {

    private List<UITile> tiles;

}