package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AskAlbumNamePane implements Observateur {

    Album album;

    @FXML
    protected TextField input_name;

    public AskAlbumNamePane(Album album) {
        this.album = album;
        album.ajouterObservateur(this);

    }

    @FXML
    protected void EnterClick() {
        String name = input_name.getText();

        if (!name.equals("")) {
            album.ChangeAlbumName(name);
        }

        album.cancel(1);
    }

    @FXML
    protected void CancelClick() {
        album.cancel(1);
    }

    @Override
    public void update() {

    }
}
