/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import model.Repository;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class OnlineModeController extends BorderPane implements Initializable {

    @FXML
    ListView searchResults;      
    
    public OnlineModeController(ObservableList<Repository> items ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/OnlineMode.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        searchResults.setItems(items);
    }
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
