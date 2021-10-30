package album.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class DroiteInventairePhotos {

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
        System.out.println(lig);
        System.out.println(col);
    }
}
