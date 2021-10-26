module album {
    requires javafx.controls;
    requires javafx.fxml;

    opens album to javafx.fxml;
    exports album;
}