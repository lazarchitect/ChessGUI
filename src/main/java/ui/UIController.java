package main.java.ui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.Constants;

public class UIController extends javafx.application.Application {

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);

        UIBuilder.drawBoard(root);

        UIBuilder.drawChesster(root);

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

}
