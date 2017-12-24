package controller;

import business_logic.gateways.GitHubGateway;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController {
      
    @FXML TextField input;
    
    @FXML
    private void GoToSignIn() throws IOException {   
        FrontController.setScene("/ihm/SignIn.fxml");
    }
    
    @FXML
    private void GoToSignUp() throws IOException {
        FrontController.setScene("/ihm/SignUp.fxml");
    }
    
    @FXML 
    private void GoToHome() throws IOException {   
        FrontController.setScene("/ihm/Home.fxml");
    }
    
    @FXML
    private void onEnter() throws IOException {        
        OnlineModeController ctrl = (OnlineModeController)(FrontController.setScene("/ihm/OnlineMode.fxml", new GitHubGateway()).getController()); 
        ctrl.setItems(input.getText());      
        ctrl.setPlaceholderSearchResults("Results loading ...");
    }
}
