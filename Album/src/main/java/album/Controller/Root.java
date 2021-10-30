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

    Pane askimagepane;
    Pane savepane;

    @FXML
    private StackPane root;

    @FXML
    protected void QuitClick() {
        root.getChildren().add(savepane);
    }

    public Root(Album album) throws IOException {
        this.album = album;
        album.ajouterObservateur(this);

        //creation observateurs
        AskImagePane askimagepane_controller = new AskImagePane(album);
        SavePane savepane_controller = new SavePane(album);

        FXMLLoader fxmlLoader_savepane = new FXMLLoader(Main.class.getResource("SavePane.fxml"));

        fxmlLoader_savepane.setControllerFactory(ic -> {
            if (ic.equals(album.Controller.SavePane.class)) return savepane_controller;
            else return null;
        });

        FXMLLoader fxmlLoader_askimagepane = new FXMLLoader(Main.class.getResource("AskImagePane.fxml"));

        fxmlLoader_askimagepane.setControllerFactory(ic -> {
            if (ic.equals(album.Controller.AskImagePane.class)) return askimagepane_controller;
            else return null;
        });

        this.savepane = (Pane) fxmlLoader_savepane.load();
        this.askimagepane = (Pane) fxmlLoader_askimagepane.load();
    }

    @Override
    public void update() {

    }
}
