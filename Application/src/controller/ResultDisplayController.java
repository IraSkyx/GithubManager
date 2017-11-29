/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import model.Repository;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class ResultDisplayController implements Initializable {

    private final ObjectProperty<Repository> repository = new SimpleObjectProperty<>();
        public final Repository getRepository()  { return repository.get(); }
        public final void setRepository(Repository value) { repository.set(value); }
        public ObjectProperty<Repository> repositoryProperty() {return repository;}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
