package mokhoase.assign2;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImageHandler {

    public static void addImageToCanvas(Stage stage, DrawingCanvas drawingCanvas) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            drawingCanvas.getGraphicsContext().drawImage(image, 100, 100);
        }
    }
}
