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

    @FXML
    private Pane savepane;

    @FXML
    protected void SaveClick() throws IOException {
        album.save();
        Platform.exit();
    }

    @FXML
    protected void DontSaveClick() {
        Platform.exit();
    }

    @FXML
    protected void CancelClick() {
        album.cancel(savepane);
    }

    public SavePane(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @Override
    public void update() {

    }
}
