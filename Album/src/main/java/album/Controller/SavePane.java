package album.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class SavePane {

    @FXML
    private Pane savepane;

    @FXML
    protected void SaveClick() {
        Platform.exit();
    }

    @FXML
    protected void DontSaveClick() {
        Platform.exit();
    }

    @FXML
    protected void CancelClick() {
        StackPane root = (StackPane) savepane.getParent();
        root.getChildren().remove(savepane);
    }
}
