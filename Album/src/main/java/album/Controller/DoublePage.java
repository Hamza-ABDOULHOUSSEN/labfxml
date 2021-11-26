package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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
    Button title2;
    TextField tf2 = new TextField();
    @FXML
    Pane pane2;

    public DoublePage(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @FXML
    protected void title1Click() {
        vbox1.getChildren().clear();
        tf1.setOnAction(e -> {
            String titre = tf1.getText();
            tf1.clear();
            album.changetitle(titre, 1);
        });
        vbox1.getChildren().addAll(tf1, pane1);
    }

    @FXML
    protected void title2Click() {
        vbox2.getChildren().clear();
        tf2.setOnAction(e -> {
            String titre = tf2.getText();
            tf2.clear();
            album.changetitle(titre, 2);
        });
        vbox2.getChildren().addAll(tf2, pane2);
    }

    @Override
    public void update() {
        ArrayList<String> doublepage_titre = album.getDoublepage_titre();
        ArrayList<ImageView> doublepage_image = album.getDoublepage_image();
        int pos = album.getDouble_page_courante();

        title1.setText(doublepage_titre.get(pos-1));
        title2.setText(doublepage_titre.get(pos));
        
        vbox1.getChildren().clear();
        vbox1.getChildren().addAll(title1, pane1);
        vbox2.getChildren().clear();
        vbox2.getChildren().addAll(title2, pane2);
        
                
    }
}
