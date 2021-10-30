package album.model;

import album.Controller.Root;
import album.Observateur.SujetObserve;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Album extends SujetObserve {
    
    // Liste: observateurs = {root , savepane, askimagepane, droiteinventairephotos}
    // pour avoir acces aux observateurs et ne pas tous les modifier à chaque fois
    
    public Album() {}
    
    public void cancel(Pane pane){
        Root root_controller = (Root) this.observateurs.get(0);
        root_controller.root.getChildren().remove(pane);

    };
    
}
