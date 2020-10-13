package main.java.ui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.util.Constants;

public class UIController extends javafx.application.Application {

    @Override
    public void start(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);

        UIBuilder.drawBoard(root);

        UIBuilder.drawChesster(root);
        UIBuilder.drawChessterText(root);
        UIBuilder.drawPiecesInitial(root);

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

}
