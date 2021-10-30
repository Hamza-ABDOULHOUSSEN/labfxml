package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class DroiteInventairePhotos implements Observateur {

    Album album;

    // donnée models
    ArrayList<ImageView> liste_photos = new ArrayList<ImageView>();
    int lig = -1;
    int col = 2;

    @FXML
    private GridPane inventaire_photos;

    @FXML
    protected void AddPhotoClick() {
        String photo = "/Images/Cat/Cat_03.jpg";
        ImageView imageview = new ImageView(new Image(getClass().getResourceAsStream(photo),100, 100, true, true));
        liste_photos.add(imageview);

        if (col == 2) {
            col = 0;
            lig++;
            inventaire_photos.setPrefHeight(100*lig);
            inventaire_photos.addRow(1);
        }
        else {
            col++;
        }

        inventaire_photos.add(imageview, col, lig);
    }

    public DroiteInventairePhotos(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @Override
    public void update() {

    }
}
