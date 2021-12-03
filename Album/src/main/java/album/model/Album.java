package album.model;

import album.Controller.AskSaveDir;
import album.Controller.DroiteInventairePhotos;
import album.Controller.Root;
import album.Observateur.SujetObserve;
import javafx.scene.layout.Pane;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Album extends SujetObserve {

    // Liste: observateurs = {root , savepane, askalbumnamepane, asksavedir, droiteinventairephotos, doublepage, information, panneaucontrole}
    // pour avoir acces aux observateurs et ne pas tous les modifier à chaque fois

    String nom_album = "";

    String save_path = "";

    int nb_double_page = 2;
    int double_page_courante = 1;

    Hashtable<Integer, String> doublepage_titre = new Hashtable<>();
    Hashtable<Integer, String> doublepage_image = new Hashtable<>();

    ArrayList<String> grid_inventory = new ArrayList<>();
    
    public Album() {}
    
    public void cancel(int stage){
        Root root_controller = (Root) this.observateurs.get(0);
        root_controller.CloseStage(stage);
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

    public Boolean save(String save_path) throws IOException, ClassNotFoundException {
        String file_path;
        if (save_path==null) {
            file_path = this.save_path + "/save.txt";
        }
        else {
            file_path = save_path + "/save.txt";
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file_path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(nom_album);
            objectOutputStream.writeObject(nb_double_page);
            objectOutputStream.writeObject(double_page_courante);
            objectOutputStream.writeObject(doublepage_titre);
            objectOutputStream.writeObject(doublepage_image);
            objectOutputStream.writeObject(grid_inventory);
            objectOutputStream.flush();
            objectOutputStream.close();

            DroiteInventairePhotos d_controller = (DroiteInventairePhotos) this.observateurs.get(4);
            d_controller.LoadInventory();
            notifierObservateurs();

            this.save_path = save_path;
            return true;
        }
        catch (FileNotFoundException e) {
            saveas();
            return false;
        }
    }

    public void saveas() {
        Root root_controller = (Root) this.observateurs.get(0);
        root_controller.OpenStage(2);
        AskSaveDir asksavedir_controller = (AskSaveDir) this.observateurs.get(3);
        asksavedir_controller.SetSaveOrRestore(0);
    }

    public Boolean restore(String save_path) throws IOException, ClassNotFoundException {
        String file_path;
        if (save_path==null) {
            file_path = this.save_path + "/save.txt";
        }
        else {
            file_path = save_path + "/save.txt";
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(file_path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            nom_album = (String) objectInputStream.readObject();
            nb_double_page = (int) objectInputStream.readObject();
            double_page_courante = (int) objectInputStream.readObject();
            doublepage_titre = (Hashtable<Integer, String>) objectInputStream.readObject();
            doublepage_image = (Hashtable<Integer, String>) objectInputStream.readObject();
            grid_inventory = (ArrayList<String>) objectInputStream.readObject();
            objectInputStream.close();
            DroiteInventairePhotos d_controller = (DroiteInventairePhotos) this.observateurs.get(4);
            d_controller.LoadInventory();
            notifierObservateurs();

            this.save_path = save_path;
            return true;
        }
        catch (FileNotFoundException e) {
            restorefrom();
            return false;
        }
    }

    public void restorefrom() {
        Root root_controller = (Root) this.observateurs.get(0);
        root_controller.OpenStage(2);
        AskSaveDir asksavedir_controller = (AskSaveDir) this.observateurs.get(3);
        asksavedir_controller.SetSaveOrRestore(1);
    }

    public void ClearInventory() {
        grid_inventory = new ArrayList<>();
        DroiteInventairePhotos d_controller = (DroiteInventairePhotos) this.observateurs.get(4);
        d_controller.LoadInventory();
    }

    public void AddImageInventory(String image_path) {
        grid_inventory.add(image_path);
    }

    public Boolean Checksave() throws IOException, ClassNotFoundException {
        Boolean isSaved = true;

        try {
            FileInputStream fileInputStream = new FileInputStream(save_path + "/save.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            String nom_album_save = (String) objectInputStream.readObject();
            int nb_double_page_save = (int) objectInputStream.readObject();
            int double_page_courante_save = (int) objectInputStream.readObject();
            Hashtable<Integer, String> doublepage_titre_save = (Hashtable<Integer, String>) objectInputStream.readObject();
            Hashtable<Integer, String> doublepage_image_save = (Hashtable<Integer, String>) objectInputStream.readObject();
            ArrayList<String> grid_inventory_save = (ArrayList<String>) objectInputStream.readObject();
            objectInputStream.close();

            if (nb_double_page_save != nb_double_page || double_page_courante_save != double_page_courante || !nom_album_save.equals(nom_album) || !doublepage_titre_save.equals(doublepage_titre) || !doublepage_image_save.equals(doublepage_image) || !grid_inventory_save.equals(grid_inventory)) {
                isSaved = false;
            }
        }
        catch (FileNotFoundException e) {
            isSaved = false;
        }

        return isSaved;
    }

    public void open() throws IOException, ClassNotFoundException {
        // restaurer depuis la derniere sauvegarde
        String save_dir_path = getClass().getResource("").getPath();
        save_dir_path = save_dir_path + "../../savedirectory/save.txt";

        FileInputStream fileInputStream = new FileInputStream(save_dir_path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        save_path = (String) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(save_path);
    }

    // GETTERS
    public String getNom_album() {return this.nom_album;}
    public int getNb_double_page() {return this.nb_double_page;}
    public int getDouble_page_courante() {return this.double_page_courante;}
    public Hashtable<Integer, String> getDoublepage_titre() {return this.doublepage_titre;}
    public Hashtable<Integer, String> getDoublepage_image() {return this.doublepage_image;}
    public ArrayList<String> getGrid_inventory() {return grid_inventory;}
}
