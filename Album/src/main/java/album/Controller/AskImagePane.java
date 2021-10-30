package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AskImagePane implements Observateur {

    Album album;

    @FXML
    private Pane askimagepane;

    @FXML
    private Label error_message;

    @FXML
    private TextArea input_path;

    @FXML
    protected void EnterClick() {
        String path = input_path.getText();
        path = "/Images" + path;
        ImageView imageview = null;

        try {
            imageview = new ImageView(new Image(getClass().getResourceAsStream(path),100, 100, true, true));
        }
        catch (NullPointerException e) {
            error_message.setText("File not found");
            return;
        }
        error_message.setText("");
        album.addphoto(imageview);
        album.cancel(askimagepane);

    }


    @FXML
    protected void CancelClick() {
        error_message.setText("");
        album.cancel(askimagepane);
    }

    public AskImagePane(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    @Override
    public void update() {

    }
}
