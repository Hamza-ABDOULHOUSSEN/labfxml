package album.Observateur;

import java.util.ArrayList;

public class SujetObserve {
    private ArrayList<Observateur> observateurs = new ArrayList<Observateur>();

    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    public void notifierObservateurs() {
        for(Observateur o : observateurs) {
            o.update();
        }
    }
}
