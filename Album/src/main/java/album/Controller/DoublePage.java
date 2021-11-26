package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DoublePage implements Observateur {

    Album album;

    @FXML
    Button title1;

    @FXML
    Button title2;

    public DoublePage(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @FXML
    protected void title1Click() {}

    @FXML
    protected void title2Click() {}

    @Override
    public void update() {

    }
}
