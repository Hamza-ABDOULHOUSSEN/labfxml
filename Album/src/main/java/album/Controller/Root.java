package album.Controller;

import album.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Root {
    @FXML
    private StackPane root;

    @FXML
    protected void QuitClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SavePane.fxml"));
        Pane pane = (Pane) fxmlLoader.load();
        root.getChildren().add(pane);
    }
}
