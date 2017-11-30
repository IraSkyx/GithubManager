package controller;

import business_logic.Repository;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import business_logic.RepositoryMaker;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

public class HomeController {

    @FXML
    TextField input;
    
    @FXML
    Label error;
    
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
        ObservableList<Repository> list = FXCollections.observableArrayList();              
        
        RepositoryService service = new RepositoryService();
        service.getClient().setOAuth2Token("5492d1456e37ba89cc8985ccdee4a8dc028e916f");
        
        for(org.eclipse.egit.github.core.Repository repo : service.getRepositories(input.getText()))
            list.add(RepositoryMaker.Make(repo));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/OnlineMode.fxml"));
        FrontController.setScene((BorderPane)loader.load());   
        ((OnlineModeController)loader.getController()).setItems(list);
    }
}
