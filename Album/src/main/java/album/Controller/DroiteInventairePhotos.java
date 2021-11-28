package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DroiteInventairePhotos implements Observateur {

    Album album;
    ArrayList<String> extensions = new ArrayList<>(Arrays.asList("gif", "jpg", "png", "jpeg", "tiff"));

    // donnée models
    ArrayList<ImageView> liste_photos = new ArrayList<>();
    int lig = -1;
    int col = 2;

    @FXML
    private GridPane inventaire_photos;

    @FXML
    protected void AddPhotoClick() {
        File dossier = new DirectoryChooser().showDialog(new Stage());
        File[] fichiers = dossier.listFiles();
        Arrays.sort(fichiers);

        if (fichiers != null) {
            for (File f: fichiers) {
                String name = f.getName();
                String ext = name.substring(name.lastIndexOf(".")+1);
                if ( extensions.contains(ext) ) {
                    String image_path = f.toURI().toString();
                    Image image = new Image(image_path, 100, 100, true, true);
                    ImageView imageview = new ImageView(image);

                    imageview.setOnDragDetected(event -> {
                        Dragboard dragboard = imageview.startDragAndDrop(TransferMode.ANY);

                        ClipboardContent clipboardContent = new ClipboardContent();
                        clipboardContent.putImage(image);
                        clipboardContent.putString(image_path);
                        dragboard.setContent(clipboardContent);

                        event.consume();
                    });

                    imageview.setOnDragDone(event -> {
                        event.consume();
                    });

                    addphoto(imageview);
                }
            }
        }
    }

    public DroiteInventairePhotos(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @Override
    public void update() {

    }

    public void addphoto(ImageView imageview) {

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
}
