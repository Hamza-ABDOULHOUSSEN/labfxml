package album.Controller;

import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AskSaveDir implements Observateur {

    Album album;

    int SaveOrRestore;      // 0 : Save  1 : Restore

    @FXML
    private TextField input_name;

    @FXML
    private Label error_message;

    public AskSaveDir(Album album) {
        this.album = album;
        album.ajouterObservateur(this);
    }

    public void SetSaveOrRestore(int SaveOrRestore) {
        this.SaveOrRestore = SaveOrRestore;
    }

    @FXML
    protected void EnterClick() throws IOException, ClassNotFoundException {
        String name = input_name.getText();
        CancelClick();
        Boolean filefound;

        if (SaveOrRestore == 0) {
            filefound = album.save(name);
        }
        else {
            filefound = album.restore(name);
        }

        if (!filefound) {
            error_message.setText("File not Found");
        }
        else {
            error_message.setText("");
        }
    }

    @FXML
    protected void CancelClick() {
        error_message.setText("");
        album.cancel(2);
    }

    @FXML
    protected void searchdir() throws IOException, ClassNotFoundException {
        File dossier = new DirectoryChooser().showDialog(new Stage());
        String savepath = dossier.getPath();
        input_name.setText(savepath);
        CancelClick();
        Boolean filefound;

        if (SaveOrRestore == 0) {
            filefound = album.save(savepath);
        }
        else {
            filefound = album.restore(savepath);
        }

        if (!filefound) {
            error_message.setText("File not Found");
        }
        else {
            error_message.setText("");
        }
    }

    @Override
    public void update() {

    }
}
