/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import business_logic.Repository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class OnlineModeController extends BorderPane implements Initializable {

    @FXML
    ListView searchResults;  

    @FXML
    ResultDisplayController selectedRepo;
    
    public void setResults(ObservableList<Repository> items ) {
        searchResults.setItems(items);
    }
    
    @FXML
    private void onEnter(){
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       selectedRepo.repositoryProperty().bind(new SimpleObjectProperty<>((Repository)searchResults.getSelectionModel().getSelectedItem()));
    }
}
