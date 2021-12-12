package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Information implements Observateur {

    Album album;

    @FXML
    protected Label nom_album_label;

    @FXML
    protected Label nb_page_label;

    public Information(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @Override
    public void update() {
        String nom_album = this.album.getNom_album();
        int nb_page = this.album.getNb_double_page();
        int page_courante = this.album.getDouble_page_courante();
        this.nom_album_label.setText("Nom de l'album : " + nom_album);
        this.nb_page_label.setText("page " + String.valueOf(page_courante) + "-" + String.valueOf(page_courante+1) + " sur " + String.valueOf(nb_page));
    }
}
