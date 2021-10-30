package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class AskImagePane implements Observateur {

    Album album;

    @FXML
    private Pane askimagepane;

    @FXML
    private Label error_message;

    @FXML
    private TextArea input_path;

    @FXML
    protected void EnterClick() {

    }

    public AskImagePane(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @Override
    public void update() {

    }
}
