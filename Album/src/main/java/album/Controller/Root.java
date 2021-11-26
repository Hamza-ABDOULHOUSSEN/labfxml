package album.Controller;

import album.Main;
import album.Observateur.Observateur;
import album.model.Album;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Root implements Observateur {

    Album album;

    Pane askalbumnamepane;
    Pane savepane;

    @FXML
    public StackPane root;

    @FXML
    protected void SaveClick() {}

    @FXML
    protected void RestoreClick() {}

    @FXML
    protected void QuitClick() {
        root.getChildren().add(savepane);
    }

    @FXML
    protected void RenameClick() {
        album.addAskAlbumNamePane();
    }

    public Root(Album album) throws IOException {
        this.album = album;
        album.ajouterObservateur(this);

        //creation observateurs
        SavePane savepane_controller = new SavePane(album);
        AskAlbumNamePane askalbumnamepane_controller = new AskAlbumNamePane(album);

        FXMLLoader fxmlLoader_savepane = new FXMLLoader(Main.class.getResource("SavePane.fxml"));

        fxmlLoader_savepane.setControllerFactory(ic -> {
            if (ic.equals(album.Controller.SavePane.class)) return savepane_controller;
            else return null;
        });

        FXMLLoader fxmlLoader_askalbumnamepane = new FXMLLoader(Main.class.getResource("AskAlbumNamePane.fxml"));

        fxmlLoader_askalbumnamepane.setControllerFactory(ic -> {
            if (ic.equals(album.Controller.AskAlbumNamePane.class)) return askalbumnamepane_controller;
            else return null;
        });

        this.savepane = (Pane) fxmlLoader_savepane.load();
        this.askalbumnamepane = (Pane) fxmlLoader_askalbumnamepane.load();
    }

    public void addAskAlbumNamePane() {
        root.getChildren().add(askalbumnamepane);
    }
    @Override
    public void update() {

    }
}
