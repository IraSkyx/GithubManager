package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import launch.Main;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

public class HomeController {

    @FXML
    private Button signIn;

    @FXML
    public void GoToSignIn(ActionEvent action) throws IOException {   
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/ihm/SignIn.fxml"));
            Scene scene = new Scene(root, 1080, 720);
            Main.getPrimaryStage().setScene(scene);
    }
    
    @FXML
    public void GoToSignUp(Event e) {
        
    }
}
