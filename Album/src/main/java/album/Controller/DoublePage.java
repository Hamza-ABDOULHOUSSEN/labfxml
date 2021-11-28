package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Hashtable;

public class DoublePage implements Observateur {

    Album album;

    @FXML
    VBox vbox1;
    @FXML
    VBox vbox2;

    @FXML
    Button title1;
    TextField tf1 = new TextField();

    @FXML
    Pane pane1;
    @FXML
    ImageView imageview1;

    @FXML
    Button title2;
    TextField tf2 = new TextField();

    @FXML
    Pane pane2;
    @FXML
    ImageView imageview2;

    public DoublePage(Album album) {
        this.album = album;
        album.ajouterObservateur(this);


        tf1.setOnAction(e -> {
            String titre = tf1.getText();
            tf1.clear();
            album.changetitle(titre, 1);
        });
        tf1.setPadding(new Insets(20, 0,20,0));

        tf2.setOnAction(e -> {
            String titre = tf2.getText();
            tf2.clear();
            album.changetitle(titre, 2);
        });
        tf2.setPadding(new Insets(20, 0,20,0));
    }

    @FXML
    protected void title1Click() {
        vbox1.getChildren().clear();
        vbox1.getChildren().addAll(tf1, pane1);
    }

    @FXML
    protected void title2Click() {
        vbox2.getChildren().clear();
        vbox2.getChildren().addAll(tf2, pane2);
    }

    @FXML
    protected void Dragover(DragEvent event) {
        event.acceptTransferModes(TransferMode.ANY);
        event.consume();
    }

    @FXML
    protected void Dragdropped1(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        Image image = null;
        String image_path = null;
        if (dragboard.hasImage()){
            image = dragboard.getImage();
            image_path = dragboard.getString();
            success = true;
        }

        event.setDropCompleted(success);

        if (image != null) {
            album.AddImage(image_path, 1);
        }

        event.consume();
    }

    @FXML
    protected void Dragdropped2(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        Image image = null;
        String image_path = null;
        if (dragboard.hasImage()){
            image = dragboard.getImage();
            image_path = dragboard.getString();
            success = true;
        }

        event.setDropCompleted(success);

        if (image != null) {
            album.AddImage(image_path, 2);
        }

        event.consume();
    }

    @Override
    public void update() {
        Hashtable<Integer, String> doublepage_titre = album.getDoublepage_titre();
        Hashtable<Integer, String> doublepage_image = album.getDoublepage_image();
        int pos = album.getDouble_page_courante();
        
        String t1 = doublepage_titre.get(pos);
        if (t1 != null) {
            if (!t1.equals("")) {
                title1.setText(t1);
            }
        }
        else {
            title1.setText("Insert title");
        }

        String t2 = doublepage_titre.get(pos+1);
        if (t2 != null) {
            if (!t2.equals("")) {
                title2.setText(t2);
            }
        }
        else {
            title2.setText("Insert title");
        }

        if (doublepage_image.get(pos) != null) {
            String image_path = doublepage_image.get(pos);
            Image image = new Image(image_path);
            imageview1.setImage(image);
        }
        else {
            imageview1.setImage(null);
        }
        if (doublepage_image.get(pos+1) != null) {
            String image_path = doublepage_image.get(pos+1);
            Image image = new Image(image_path);
            imageview2.setImage(image);
        }
        else {
            imageview2.setImage(null);
        }
        
        vbox1.getChildren().clear();
        vbox1.getChildren().addAll(title1, pane1);
        vbox2.getChildren().clear();
        vbox2.getChildren().addAll(title2, pane2);
    }
}
