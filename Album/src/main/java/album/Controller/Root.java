package album.Controller;

import album.Main;
import album.Observateur.Observateur;
import album.model.Album;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Root implements Observateur {

    Album album;

    @FXML
    public StackPane root;

    protected Stage stageAsk = new Stage();
    protected Stage stageSave = new Stage();
    protected Stage stageSaveDir = new Stage();

    public Root(Album album) throws IOException {
        this.album = album;
        album.ajouterObservateur(this);

        //creation observateurs
        SavePane savepane_controller = new SavePane(album);

        FXMLLoader fxmlLoader_savepane = new FXMLLoader(Main.class.getResource("SavePane.fxml"));

        fxmlLoader_savepane.setControllerFactory(ic -> {
            if (ic.equals(album.Controller.SavePane.class)) return savepane_controller;
            else return null;
        });

        Pane savepane = (Pane) fxmlLoader_savepane.load();

        AskAlbumNamePane askalbumnamepane_controller = new AskAlbumNamePane(album);

        FXMLLoader fxmlLoader_askalbumnamepane = new FXMLLoader(Main.class.getResource("AskAlbumNamePane.fxml"));

        fxmlLoader_askalbumnamepane.setControllerFactory(ic -> {
            if (ic.equals(album.Controller.AskAlbumNamePane.class)) return askalbumnamepane_controller;
            else return null;
        });

        Pane askalbumnamepane = (Pane) fxmlLoader_askalbumnamepane.load();

        AskSaveDir asksavedir_controller = new AskSaveDir(album);

        FXMLLoader fxmlLoader_asksavedir = new FXMLLoader(Main.class.getResource("AskSaveDir.fxml"));

        fxmlLoader_asksavedir.setControllerFactory(ic -> {
            if (ic.equals(album.Controller.AskSaveDir.class)) return asksavedir_controller;
            else return null;
        });

        Pane asksavedirpane = (Pane) fxmlLoader_asksavedir.load();

        Scene sceneSave = new Scene(savepane, 400, 200);
        stageSave.setTitle("Save Album");
        stageSave.setScene(sceneSave);

        Scene sceneAsk = new Scene(askalbumnamepane, 400, 250);
        stageAsk.setTitle("Rename Album");
        stageAsk.setScene(sceneAsk);

        Scene sceneSaveDir = new Scene(asksavedirpane, 400, 250);
        stageSaveDir.setTitle("Insert Save Directory");
        stageSaveDir.setScene(sceneSaveDir);
    }

    @FXML
    protected void NewAlbumClick() {
        album.newAlbum();
    }

    @FXML
    protected void SaveClick() throws IOException, ClassNotFoundException {
        album.save(null);
    }

    @FXML
    protected void SaveAsClick() {
        album.saveas();
    }

    @FXML
    protected void RestoreClick() throws IOException, ClassNotFoundException {
        album.restore(null);
    }

    @FXML
    protected void RestoreFromClick() {
        album.restorefrom();
    }

    @FXML
    protected void QuitClick() throws IOException, ClassNotFoundException {
        Boolean isSaved = album.Checksave();
        if (!isSaved) {
            stageSave.show();
        }
        else {
            Platform.exit();
        }
    }

    @FXML
    protected void RenameClick() throws IOException {
        stageAsk.show();
    }

    public void OpenStage(int stage) {
        if (stage == 0) {
            stageSave.show();
        }
        else if (stage == 1){
            stageAsk.show();
        }
        else {
            stageSaveDir.show();
        }
    }

    public void CloseStage(int stage) {
        if (stage == 0) {
            stageSave.close();
        }
        else if (stage == 1){
            stageAsk.close();
        }
        else {
            stageSaveDir.close();
        }
    }

    @FXML
    protected void ClearInvent() {
        album.ClearInventory();
    }

    @Override
    public void update() {

    }
}
