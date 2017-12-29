/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business_logic.user.IUser;
import business_logic.user.UsersManager;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author fsimo
 */
public class OptionController {

    @FXML TextField username;
    @FXML TextField email;
    @FXML TextField password;
    @FXML Label error;
    
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        setUsername();
        setPassword();
        getEmail();
    }    
    
    @FXML
    private void GoOnlineMode() throws IOException {
        FrontController.setScene("/ihm/OnlineMode.fxml");
    } 
    
    private void setUsername() {
        username.textProperty().bindBidirectional(UsersManager.getCurrentUser().usernameProperty());
    }
    
    private void setPassword() {
        password.textProperty().bindBidirectional(UsersManager.getCurrentUser().passwordProperty());
    }
    
    private void getEmail() {
        email.textProperty().bindBidirectional(UsersManager.getCurrentUser().emailProperty());
        if(UsersManager.exists(email.textProperty().get())) error.setVisible(true); 
        else error.setVisible(false);
    }
}
