package main.java.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller extends javafx.application.Application {

    public Label helloWorld;
    //public GridPane board;

    int TILE_SIZE = 80;

    public void sayHelloWorld() {
        helloWorld.setText("Hello World!");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = FXMLLoader.load(getClass().getResource("../../fxml/sample.fxml"));

        Scene scene = new Scene(root, 800, 800);

        final Canvas canvas = new Canvas(800,800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        paintBoard(gc);

        root.getChildren().add(canvas);


        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void paintBoard(GraphicsContext gc){
        for(int y = 0; y < 8; y++){

            paintRow(y, gc);

        }
    }

    private void paintRow(int y, GraphicsContext gc){
        for(int x = 0; x <8; x++){
            paintTile(y, x, gc);
        }
    }

    private void paintTile(int y, int x, GraphicsContext gc){
        if((y%2==0 && x%2==0) || (y%2==1 && x%2==1)){
            gc.setFill(Color.web("#DDDDDD"));
        }
        else{
            gc.setFill(Color.web("#333333"));
        }
        gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
