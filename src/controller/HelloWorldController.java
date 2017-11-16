package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

public class HelloWorldController {

    @FXML
    private Label labelAAfficher;

    @FXML
    public void actionAfficher(Event e) {
        String res="";
        RepositoryService service = new RepositoryService();
        try {
            for (Repository repo : service.getRepositories("IraSkyx"))
                res+= repo.getName() + "\n";
        } catch (IOException ex) {
            Logger.getLogger(HelloWorldController.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelAAfficher.setText(res);
        labelAAfficher.setVisible(true);
    }
    
}
