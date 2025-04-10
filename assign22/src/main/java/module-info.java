module mokhoase.assign2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens mokhoase.assign2 to javafx.fxml;
    exports mokhoase.assign2;
}