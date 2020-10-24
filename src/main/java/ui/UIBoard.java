package main.java.ui;

import javafx.scene.Group;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class UIBoard extends Group {

    private List<UIRow> uiRows;

    public UIBoard(){
        uiRows = new ArrayList<>();
    }




}
