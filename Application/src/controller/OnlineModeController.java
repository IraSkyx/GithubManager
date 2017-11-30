/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business_logic.Category;
import business_logic.Follow;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import business_logic.Repository;
import business_logic.RepositoryMaker;
import business_logic.User;
import java.io.IOException;
import javafx.beans.binding.Bindings;
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
    private void onEnter() throws IOException{
        ObservableList<Repository> list = FXCollections.observableArrayList();
        
        for(org.eclipse.egit.github.core.Repository repo : new RepositoryService().getRepositories(input.getText()))
            list.add(RepositoryMaker.Make(repo));

        setItems(list);
    }

    public void setItems(ObservableList<Repository> results) {
        searchResults.setItems(results);
        searchResults.getSelectionModel().select(0);    
        repoName.textProperty().bind(Bindings.selectString(searchResults.getSelectionModel().selectedItemProperty(), "name"));
        repoDescription.textProperty().bind(Bindings.selectString(searchResults.getSelectionModel().selectedItemProperty(), "description"));
        repoReadMe.textProperty().bind(Bindings.selectString(searchResults.getSelectionModel().selectedItemProperty(), "readme"));
        
        //setFollows();
    }

    /*private void setFollows() {
        User user = new User("Adrien", "Lenoir", "adrien.lenoir@gmail.com");
        user.getFollows().AddFollow(new Category("PHP"));
        
        for(Follow follow : ((Category)user.getFollows()).getListOfFollows()){
            if(follow instanceof Category){
                AddListView((Category)follow);
            }
            else{
                
            }
        }
    }

    private void AddListView(Category categ) {
        for(Follow follow : categ.getListOfFollows())
            if(follow instanceof Category){
                searchResults.getItems().add(new ListView());
                AddListView((Category)follow);
            }     
            else
                                
    }*/
}
