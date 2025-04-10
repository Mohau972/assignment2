package mokhoase.assign2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Interactive Whiteboard");

        DrawingCanvas drawingCanvas = new DrawingCanvas();
        BorderPane root = new BorderPane();
        ControlPanel controlPanel = new ControlPanel(stage, drawingCanvas, root);

        root.setLeft(controlPanel);
        root.setCenter(drawingCanvas.getCanvas());

        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
