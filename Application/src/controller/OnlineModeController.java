/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business_logic.APIGateway;
import business_logic.Category;
import business_logic.Follow;
import business_logic.ModelGateway;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import business_logic.Repository;
import business_logic.UsersManager;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;

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
    
    @FXML TreeView<Follow> TreeViewFollows;
    
    @FXML Label h31;
    
    @FXML Button loggedOff1;
    
    @FXML Button loggedOff2;
    
    @FXML Label loggedIn1;
    
    @FXML Button loggedIn2;
    
    @FXML TreeItem root;
    
    @FXML
    private void onEnter() {   
        Platform.runLater(() -> {
            setItems(APIGateway.getRepositoriesByUsername(input.getText()));
        });     
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
        ModelGateway.disconnect();       
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
             
        UsersManager.currentUserProperty().get().myFollowProperty().get().addListeners(new ListChangeListener<Follow>(){
            @Override
            public void onChanged(ListChangeListener.Change c) {
                updateTreeView(root);
            }
        });    
        root.valueProperty().bind(UsersManager.currentUserProperty().get().myFollowProperty());
    }

    private void updateTreeView(TreeItem<Follow> root) {
        System.out.println("Hello");
        ((Category)root.getValue()).getListOfFollows().stream().forEach(x -> {
            
            TreeItem<Follow> item = new TreeItem<>(x);
            
            if(x instanceof Repository && !root.getChildren().parallelStream().anyMatch(y -> ((Repository)y.getValue()).getProxy().generateId().equals(((Repository)x).getProxy().generateId())))
                root.getChildren().add(item);
            else          
                updateTreeView(item);
        });
    }
}
