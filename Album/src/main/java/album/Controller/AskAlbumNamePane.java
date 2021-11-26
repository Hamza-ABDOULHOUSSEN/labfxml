package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AskAlbumNamePane implements Observateur {

    Album album;

    @FXML
    private Pane askalbumnamepane;

    @FXML
    private TextArea input_name;

    @FXML
    protected void EnterClick() {
        String name = input_name.getText();

        album.ChangeAlbumName(name);
        album.cancel(askalbumnamepane);

    }


    @FXML
    protected void CancelClick() {
        album.cancel(askalbumnamepane);
    }

    public AskAlbumNamePane(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @Override
    public void update() {

    }
}
