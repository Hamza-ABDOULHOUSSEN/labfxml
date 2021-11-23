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
        album.precedent_double_page();
    }

    @FXML
    protected void suivantClick() {
        album.suivant_double_page();
    }

    @FXML
    protected void ajouterClick() {
        album.ajouter_double_page();
    }

    @FXML
    protected void retirerClick() {
        album.retirer_double_page();
    }

    @Override
    public void update() {

    }
}
