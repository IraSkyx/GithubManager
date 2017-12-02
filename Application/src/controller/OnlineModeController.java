/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business_logic.APIGateway;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import business_logic.Repository;
import business_logic.UsersManager;
import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class OnlineModeController extends BorderPane {
    
    @FXML ListView searchResults;  
    
    @FXML ListView userFollows;
    
    @FXML TextField input;
    
    @FXML Label repoName;
    
    @FXML Label repoDescription; 
    
    @FXML Label repoReadMe; 
    
    @FXML TreeView TreeViewFollows;
    
    @FXML Label h31;
    
    @FXML Button loggedOff1;
    
    @FXML Button loggedOff2;
    
    @FXML Label loggedIn1;
    
    @FXML Button loggedIn2;
    
    @FXML
    private void onEnter() {      
        setItems(APIGateway.getRepositories(input.getText()));
    }
    
    @FXML
    private void GoHome() throws IOException{
        FrontController.setScene((BorderPane)FXMLLoader.load(getClass().getResource("/ihm/Home.fxml")));
    }
    
    @FXML
    private void SignIn() throws IOException{
        FrontController.setScene((BorderPane)FXMLLoader.load(getClass().getResource("/ihm/SignIn.fxml")));
    }
    
    @FXML
    private void SignUp() throws IOException{
        FrontController.setScene((BorderPane)FXMLLoader.load(getClass().getResource("/ihm/SignUp.fxml")));
    }
    
    @FXML
    private void logOff() throws IOException{
        UsersManager.disconnect();       
    }

    public void setItems(ObservableList<Repository> results) {
        searchResults.setItems(results);      
        
        repoName.textProperty().bind(Bindings.selectString(searchResults.getSelectionModel().selectedItemProperty(), "name"));
        repoDescription.textProperty().bind(Bindings.selectString(searchResults.getSelectionModel().selectedItemProperty(), "description"));
        repoReadMe.textProperty().bind(Bindings.selectString(searchResults.getSelectionModel().selectedItemProperty(), "readme"));            
    }
    
    public void initialize(){   
        
        BooleanBinding nullToBool = Bindings
            .when(UsersManager.currentUserProperty().isNull())
            .then(true)
            .otherwise(false);
        
        BooleanBinding nullToBool2 = Bindings
            .when(UsersManager.currentUserProperty().isNull())
            .then(false)
            .otherwise(true);
        
        h31.visibleProperty().bind(nullToBool2); 
        h31.managedProperty().bind(nullToBool2);        
        
        loggedIn1.visibleProperty().bind(nullToBool2);     
        loggedIn2.visibleProperty().bind(nullToBool2);           
        loggedOff1.visibleProperty().bind(nullToBool);        
        loggedOff2.visibleProperty().bind(nullToBool); 
        
        loggedIn1.textProperty().bind(Bindings.format("Welcome %s", UsersManager.currentUserProperty().asString()));
        
        loggedIn1.managedProperty().bind(nullToBool2);     
        loggedIn2.managedProperty().bind(nullToBool2);           
        loggedOff1.managedProperty().bind(nullToBool);        
        loggedOff2.managedProperty().bind(nullToBool);
        
        TreeViewFollows.visibleProperty().bind(nullToBool2);
        TreeViewFollows.managedProperty().bind(nullToBool2);
    }
}
