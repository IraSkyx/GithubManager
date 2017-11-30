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
import business_logic.RepositoryMaker;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.eclipse.egit.github.core.service.RepositoryService;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class OnlineModeController extends BorderPane {
    
    @FXML
    ListView searchResults;  
    
    @FXML
    TextField input;
    
    @FXML
    Label repoName;
    
    @FXML
    private void onEnter() throws IOException{
        ObservableList<Repository> list = FXCollections.observableArrayList();
        
        for(org.eclipse.egit.github.core.Repository repo : new RepositoryService().getRepositories(input.getText()))
            list.add(RepositoryMaker.Make(repo));

        setItems(list);
    }

    public void setItems(ObservableList<Repository> results) {
        searchResults.setItems(results);
    }
    
    public void initialize(){
        searchResults.getSelectionModel().select(1);        
        repoName.textProperty().bind(searchResults.getSelectionModel().selectedItemProperty().asString());
    }
}
