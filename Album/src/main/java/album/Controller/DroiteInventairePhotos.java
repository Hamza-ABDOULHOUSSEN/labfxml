package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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

                    addphoto(image_path);
                    album.AddImageInventory(image_path);
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
        // On ne met pas à jour la grille à chaque action, ca prendrait trop de temps
    }

    public void addphoto(String image_path) {
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Add Left");
        MenuItem mi2 = new MenuItem("Add Right");
        mi1.setOnAction(e->{
            album.AddImage(image_path, 1);
        });
        mi2.setOnAction(e->{
            album.AddImage(image_path, 2);
        });
        cm.getItems().addAll(mi1, mi2);
        Image image = new Image(image_path, 100, 100, true, true);
        ImageView imageview = new ImageView(image);
        imageview.setOnContextMenuRequested(e -> cm.show(imageview, e.getScreenX(), e.getScreenY()));

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

        liste_photos.add(imageview);

        int taille = liste_photos.size() - 1;
        int col = taille % 3;
        int lig = taille / 3;

        if (col == 0) {
            inventaire_photos.setPrefHeight(100*lig);
            inventaire_photos.addRow(1);
        }

        inventaire_photos.add(imageview, col, lig);
    }

    public void LoadInventory() {

        inventaire_photos.getChildren().clear();
        inventaire_photos.getRowConstraints().clear();
        System.err.println(inventaire_photos.getRowCount());


        liste_photos = new ArrayList<>();

        ArrayList<String> grid_inventory = album.getGrid_inventory();

        for (String image_path : grid_inventory) {
            addphoto(image_path);
        }

    }
}
