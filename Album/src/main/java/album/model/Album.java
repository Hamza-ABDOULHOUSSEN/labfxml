package album.model;

import album.Controller.DroiteInventairePhotos;
import album.Controller.Root;
import album.Observateur.SujetObserve;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;

public class Album extends SujetObserve {
    
    // Liste: observateurs = {root , savepane, askimagepane, droiteinventairephotos}
    // pour avoir acces aux observateurs et ne pas tous les modifier à chaque fois

    String nom_album = "";

    int nb_double_page = 2;
    int double_page_courante = 1;

    ArrayList<String> doublepage_titre = new ArrayList<String>(Arrays.asList("titre", "titre"));
    ArrayList<ImageView> doublepage_image = new ArrayList<ImageView>(Arrays.asList(new ImageView(), new ImageView()));
    
    public Album() {}

    public void addAskimagepane() {
        Root root_controller = (Root) this.observateurs.get(0);
        root_controller.addAskimagepane();
    }
    
    public void cancel(Pane pane){
        Root root_controller = (Root) this.observateurs.get(0);
        root_controller.root.getChildren().remove(pane);
    };

    public void addphoto(ImageView imageview) {
        DroiteInventairePhotos droiteinventairephotos_controller = (DroiteInventairePhotos) this.observateurs.get(3);
        droiteinventairephotos_controller.addphoto(imageview);
    }

    public void ajouter_double_page() {
        nb_double_page+=2;
        doublepage_titre.add("titre");
        doublepage_titre.add("titre");
        doublepage_image.add(new ImageView());
        doublepage_image.add(new ImageView());
        notifierObservateurs();
    }

    public void retirer_double_page() {
        if (nb_double_page != 2) {
            nb_double_page-=2;
            doublepage_titre.remove(double_page_courante-1);
            doublepage_titre.remove(double_page_courante-1);
            doublepage_image.remove(double_page_courante-1);
            doublepage_image.remove(double_page_courante-1);
        }
        notifierObservateurs();
    }

    public void precedent_double_page() {
        if (double_page_courante != 1) {
            double_page_courante-=2;
        }
        notifierObservateurs();
    }

    public void suivant_double_page() {
        if (double_page_courante != nb_double_page - 1) {
            double_page_courante+=2;
        }
        notifierObservateurs();
    }

    public void changetitle(String titre, int page) {
        int pos;
        if (page==1) {
            pos = double_page_courante - 1;
        }
        else {
            pos = double_page_courante;
        }

        doublepage_titre.set(pos, titre);

        notifierObservateurs();

    }


    // GETTERS
    public String getNom_album() {return this.nom_album;}
    public int getNb_double_page() {return this.nb_double_page;}
    public int getDouble_page_courante() {return this.double_page_courante;}
    public ArrayList<String> getDoublepage_titre() {return this.doublepage_titre;}
    public ArrayList<ImageView> getDoublepage_image() {return this.doublepage_image;}
}
