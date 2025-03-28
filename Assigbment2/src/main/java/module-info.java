module com.example.assigbment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;


    opens com.example.assigbment2 to javafx.fxml;
    exports com.example.assigbment2;
}