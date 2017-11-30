package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import business_logic.RepositoryMaker;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

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
        ArrayList<business_logic.Repository> list = new ArrayList<>();
        for(org.eclipse.egit.github.core.Repository repo : new RepositoryService().getRepositories(input.getText()))
            list.add(RepositoryMaker.Make(repo));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/OnlineMode.fxml"));
        FrontController.setScene((BorderPane)loader.load());
        loader.<OnlineModeController>getController().setResults(FXCollections.observableList(list));
    }
}
