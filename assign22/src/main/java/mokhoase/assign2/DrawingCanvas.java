package mokhoase.assign2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawingCanvas {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private Color currentColor = Color.BLACK;
    private double prevX, prevY;

    public DrawingCanvas() {
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2);
        gc.setStroke(currentColor);

        canvas.setOnMousePressed(this::startDrawing);
        canvas.setOnMouseDragged(this::draw);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public void setColor(Color color) {
        currentColor = color;
        gc.setStroke(currentColor);
    }

    public void clear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void startDrawing(MouseEvent event) {
        prevX = event.getX();
        prevY = event.getY();
    }

    private void draw(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        gc.strokeLine(prevX, prevY, x, y);
        prevX = x;
        prevY = y;
    }
}
