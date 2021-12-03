package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AskSaveDir implements Observateur {

    Album album;

    @FXML
    private TextField input_name;

    public AskSaveDir(Album album) {
        this.album = album;
        album.ajouterObservateur(this);

    }

    @FXML
    protected void EnterClick() throws IOException, ClassNotFoundException {
        String name = input_name.getText();

        if (!name.equals("")) {
            album.SetSavePath(name);
            CancelClick();
            album.restore();
        }

    }

    @FXML
    protected void CancelClick() {
        album.cancel(2);
    }

    @FXML
    protected void searchdir() throws IOException, ClassNotFoundException {
        File dossier = new DirectoryChooser().showDialog(new Stage());
        String savepath = dossier.getPath();
        album.SetSavePath(savepath);
        CancelClick();
        album.restore();
    }

    @Override
    public void update() {

    }
}
