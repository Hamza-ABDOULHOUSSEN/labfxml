package album;

import album.Controller.*;
import album.model.Album;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {

        // creation modèle
        Album album = new Album();

        // creation observateurs
        Root root = new Root(album);
        DroiteInventairePhotos droiteinventairephotos = new DroiteInventairePhotos(album);
        DoublePage doublepage = new DoublePage(album);
        Information information = new Information(album);
        PanneauControle panneaucontrole = new PanneauControle(album);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Root.fxml"));

        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(album.Controller.Root.class)) return root;
            else if (ic.equals(album.Controller.DroiteInventairePhotos.class)) return droiteinventairephotos;
            else if (ic.equals(album.Controller.DoublePage.class)) return doublepage;
            else if (ic.equals(album.Controller.Information.class)) return information;
            else if (ic.equals(album.Controller.PanneauControle.class)) return panneaucontrole;
            else return null;
        });

        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Album Photo");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}