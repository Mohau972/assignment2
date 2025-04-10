package mokhoase.assign2;

import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;

public class TextHandler {

    public static void addTextToCanvas(DrawingCanvas drawingCanvas) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Text");
        dialog.setHeaderText("Enter text to add to the canvas:");

        dialog.showAndWait().ifPresent(text -> {
            drawingCanvas.getGraphicsContext().setFont(new Font(20));
            drawingCanvas.getGraphicsContext().fillText(text, 50, 50);
        });
    }
}
