module album {
    requires javafx.controls;
    requires javafx.fxml;

    opens album to javafx.fxml;
    exports album;

    opens album.Controller to javafx.fxml;
    exports album.Controller;
}