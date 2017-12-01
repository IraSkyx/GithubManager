package controller;

import business_logic.APIGateway;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class HomeController {

    @FXML
    TextField input;
    
    @FXML
    private void GoToSignIn() throws IOException {   
        FrontController.setScene((BorderPane)FXMLLoader.load(getClass().getResource("/ihm/SignIn.fxml")));
    }
    
    @FXML
    private void GoToSignUp() throws IOException {
        FrontController.setScene((BorderPane)FXMLLoader.load(getClass().getResource("/ihm/SignUp.fxml")));
    }
    
    @FXML 
    private void GoToHome() throws IOException {   
        FrontController.setScene((BorderPane)FXMLLoader.load(getClass().getResource("/ihm/Home.fxml")));
    }
    
    @FXML 
    private void GoToOfflineMode() throws IOException {   
        FrontController.setScene((BorderPane)FXMLLoader.load(getClass().getResource("/ihm/OfflineMode.fxml")));
    }
    
    @FXML
    private void onEnter() throws IOException {        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/OnlineMode.fxml"));
        FrontController.setScene((BorderPane)loader.load());   
        ((OnlineModeController)loader.getController()).setItems(APIGateway.getRepositories(input.getText()));
    }
}
