package album.model;

import album.Controller.Root;
import album.Observateur.SujetObserve;
import javafx.scene.layout.Pane;

import java.io.*;
import java.util.Hashtable;

public class Album extends SujetObserve {

    // Liste: observateurs = {root , savepane, askalbumnamepane, droiteinventairephotos, doublepage, information, panneaucontrole}
    // pour avoir acces aux observateurs et ne pas tous les modifier à chaque fois

    String nom_album = "";

    int nb_double_page = 2;
    int double_page_courante = 1;

    Hashtable<Integer, String> doublepage_titre = new Hashtable<>();
    Hashtable<Integer, String> doublepage_image = new Hashtable<>();
    
    public Album() throws IOException {}

    public void addAskAlbumNamePane() {
        Root root_controller = (Root) this.observateurs.get(0);
        root_controller.addAskAlbumNamePane();
    }
    
    public void cancel(Pane pane){
        Root root_controller = (Root) this.observateurs.get(0);
        root_controller.root.getChildren().remove(pane);
    }

    public void ajouter_double_page() {

        // arranger position
        for (Integer i = nb_double_page; i>double_page_courante+1; i--) {
            if (doublepage_image.get(i) != null) {
                doublepage_image.put(i+2, doublepage_image.get(i));
                doublepage_titre.put(i+2, doublepage_titre.get(i));
                doublepage_image.remove(i);
                doublepage_titre.remove(i);
            }
        }

        nb_double_page+=2;
        suivant_double_page();
    }

    public void retirer_double_page() {
        if (nb_double_page != 2) {
            doublepage_titre.remove(double_page_courante);
            doublepage_titre.remove(double_page_courante+1);
            doublepage_image.remove(double_page_courante);
            doublepage_image.remove(double_page_courante+1);

            for (Integer i = double_page_courante+2; i<nb_double_page+1; i++) {
                if (doublepage_image.get(i) != null) {
                    doublepage_image.put(i-2, doublepage_image.get(i));
                    doublepage_image.remove(i);
                    doublepage_titre.put(i-2, doublepage_titre.get(i));
                    doublepage_titre.remove(i);
                }
            }

            if (double_page_courante == nb_double_page - 1) {
                double_page_courante-=2;
            }

            nb_double_page-=2;
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
        int pos = double_page_courante;
        if (page==2) {
            pos++;
        }

        if (!titre.equals("")) {
            doublepage_titre.put(pos, titre);
        }
        notifierObservateurs();
    }

    public void ChangeAlbumName(String name) {
        nom_album = name;
        notifierObservateurs();
    }

    public void AddImage(String image_path, int page) {
        int pos = double_page_courante;
        if (page==2) {
            pos++;
        }

        Boolean changetitre = false;
        if (doublepage_titre.get(pos) == null) {
            changetitre = true;
        }
        if (doublepage_image.get(pos) != null) {
            String prevpath = doublepage_image.get(pos);
            String name = prevpath.substring(prevpath.lastIndexOf("/")+1, prevpath.lastIndexOf("."));
            if (name.equals(doublepage_titre.get(pos))) {
                changetitre = true;
            }
        }
        if (changetitre) {
            String name = image_path.substring(image_path.lastIndexOf("/")+1, image_path.lastIndexOf("."));
            doublepage_titre.put(pos, name);
        }

        doublepage_image.put(pos, image_path);
        notifierObservateurs();
    }

    public void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("./src/main/resources/save/save.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(doublepage_titre);
        objectOutputStream.writeObject(doublepage_image);

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public void restore() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("./src/main/resources/save/save.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        doublepage_titre = (Hashtable<Integer, String>) objectInputStream.readObject();
        doublepage_image = (Hashtable<Integer, String>) objectInputStream.readObject();
        objectInputStream.close();
        notifierObservateurs();
    }


    // GETTERS
    public String getNom_album() {return this.nom_album;}
    public int getNb_double_page() {return this.nb_double_page;}
    public int getDouble_page_courante() {return this.double_page_courante;}
    public Hashtable<Integer, String> getDoublepage_titre() {return this.doublepage_titre;}
    public Hashtable<Integer, String> getDoublepage_image() {return this.doublepage_image;}
}
