package com.example.assignment2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class WhiteboardApplication extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private Color currentColor = Color.BLACK;
    private double prevX, prevY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Interactive Whiteboard");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Canvas for drawing
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2);
        gc.setStroke(currentColor);

        canvas.setOnMousePressed(this::startDrawing);
        canvas.setOnMouseDragged(this::draw);

        // Control Panel
        VBox controls = new VBox(10);
        controls.setPadding(new Insets(10));
        controls.setStyle("-fx-background-color: #f4f4f4; -fx-border-radius: 10; -fx-border-color: gray;");

        Button clearButton = new Button("Clear Canvas");
        clearButton.setOnAction(e -> gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()));

        Button addTextButton = new Button("Add Text");
        addTextButton.setOnAction(e -> addTextToCanvas());

        Button addImageButton = new Button("Add Image");
        addImageButton.setOnAction(e -> addImageToCanvas(stage));

        Button addVideoButton = new Button("Add Video");
        addVideoButton.setOnAction(e -> addVideoToCanvas(stage, root));

        Button addMusicButton = new Button("Add Music");
        addMusicButton.setOnAction(e -> addMusicToCanvas(stage));

        // Color Picker
        ColorPicker colorPicker = new ColorPicker(currentColor);
        colorPicker.setOnAction(e -> setColor(colorPicker.getValue()));

        controls.getChildren().addAll(clearButton, addTextButton, addImageButton, addVideoButton, addMusicButton, colorPicker);

        root.setLeft(controls);
        root.setCenter(canvas);

        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.show();
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

    private void setColor(Color color) {
        currentColor = color;
        gc.setStroke(currentColor);
    }

    private void addTextToCanvas() {
        TextInputDialog textDialog = new TextInputDialog();
        textDialog.setTitle("Enter Text");
        textDialog.setHeaderText("Enter text to add to the canvas:");
        textDialog.showAndWait().ifPresent(text -> {
            gc.setFont(new Font(20));
            gc.fillText(text, 50, 50);
        });
    }

    private void addImageToCanvas(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            gc.drawImage(image, 100, 100);
        }
    }

    private void addVideoToCanvas(Stage stage, BorderPane root) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mov"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaView.setFitWidth(400);
            mediaView.setFitHeight(300);
            root.setBottom(mediaView);
            mediaPlayer.play();
        }
    }

    private void addMusicToCanvas(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }
}
