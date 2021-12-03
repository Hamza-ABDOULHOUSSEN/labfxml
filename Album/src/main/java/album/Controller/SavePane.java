package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class SavePane implements Observateur {

    Album album;

    public SavePane(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @FXML
    protected void SaveClick() throws IOException, ClassNotFoundException {
        Boolean success = album.save(null);
        album.cancel(0);
        if (success) {
            Platform.exit();
        }
    }

    @FXML
    protected void DontSaveClick() {
        Platform.exit();
    }

    @FXML
    protected void CancelClick() {
        album.cancel(0);
    }

    @Override
    public void update() {

    }
}
