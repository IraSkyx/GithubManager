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
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class OnlineModeController extends BorderPane {
    
    @FXML
    ListView searchResults;  
    
    @FXML
    ListView userFollows;
    
    @FXML
    TextField input;
    
    @FXML
    Label repoName;
    
    @FXML
    Label repoDescription; 
    
    @FXML
    Label repoReadMe; 
    
    @FXML
    TreeView TreeViewFollows;
    
    @FXML
    private void onEnter() {      
        setItems(APIGateway.getRepositories(input.getText()));
    }

    public void setItems(ObservableList<Repository> results) {
        searchResults.setItems(results);
        searchResults.getSelectionModel().select(0); //Little workaround : Default selection is none, so NULL => error
        
        repoName.textProperty().bind(Bindings.selectString(searchResults.getSelectionModel().selectedItemProperty(), "name"));
        repoDescription.textProperty().bind(Bindings.selectString(searchResults.getSelectionModel().selectedItemProperty(), "description"));
        repoReadMe.textProperty().bind(Bindings.selectString(searchResults.getSelectionModel().selectedItemProperty(), "readme"));
    }
    
    public void initialize(){
        
    }
}
