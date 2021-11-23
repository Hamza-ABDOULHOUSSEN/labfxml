package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;

public class PanneauControle implements Observateur {

    Album album;

    public PanneauControle(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @FXML
    protected void precedentClick() {
    }

    @FXML
    protected void suivantClick() {
    }

    @FXML
    protected void ajouterClick() {
        album.ajouter_double_page();
    }

    @FXML
    protected void retirerClick() {
    }

    @Override
    public void update() {

    }
}
