package mokhoase.assign2;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControlPanel extends VBox {

    public ControlPanel(Stage stage, DrawingCanvas drawingCanvas, BorderPane root) {
        setSpacing(10);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #f4f4f4; -fx-border-radius: 10; -fx-border-color: gray;");

        Button clearButton = new Button("Clear Canvas");
        clearButton.setOnAction(e -> drawingCanvas.clear());

        Button addTextButton = new Button("Add Text");
        addTextButton.setOnAction(e -> TextHandler.addTextToCanvas(drawingCanvas));

        Button addImageButton = new Button("Add Image");
        addImageButton.setOnAction(e -> ImageHandler.addImageToCanvas(stage, drawingCanvas));

        Button addVideoButton = new Button("Add Video");
        addVideoButton.setOnAction(e -> MediaHandler.addVideoToCanvas(stage, root));

        Button addMusicButton = new Button("Add Music");
        addMusicButton.setOnAction(e -> MediaHandler.addMusicToCanvas(stage));

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> drawingCanvas.setColor(colorPicker.getValue()));

        getChildren().addAll(clearButton, addTextButton, addImageButton, addVideoButton, addMusicButton, colorPicker);
    }
}
