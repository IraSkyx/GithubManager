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
import java.awt.Component;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    
    StringProperty intermediaryEmailProperty = new SimpleStringProperty(UsersManager.getCurrentUser().getEmail());
    
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        setUsername();
        setPassword();
        getEmail();
    }    
    
    /**
     * Go to the OnlineMode page
     * @throws IOException 
     */
    @FXML
    private void GoOnlineMode() throws IOException {
        FrontController.setScene("/ihm/OnlineMode.fxml");
    } 
    
    /**
     * Create a bidirectional binding which allow the current user to update his username
     */
    private void setUsername() {
        username.textProperty().bindBidirectional(UsersManager.getCurrentUser().usernameProperty());
    }
    
    /**
     * Create a bidirectional binding which allow the current user to update his password
     */
    private void setPassword() {
        password.textProperty().bindBidirectional(UsersManager.getCurrentUser().passwordProperty());
    }
     
    /**
     * Create a bidirectional binding which allow the current user to update his email in an intermediary property
     * This property is then checked in an other method before updating the value of the current user email property
     */
    private void getEmail() {
        email.textProperty().bindBidirectional(intermediaryEmailProperty); 
    }
    
    /**
     * Check if the email changed by the user is not already used
     * Update the value of the current user email if it is free
     * Display an error message and replace by the older value of email if it is not
     */
    @FXML
    private void validateEmailChanges() {
        if(UsersManager.exists(email.textProperty().get()) && !email.textProperty().get().equals(UsersManager.getCurrentUser().emailProperty())) {
            error.setVisible(true); 
            email.textProperty().set(intermediaryEmailProperty.get());
        }
        else { 
            error.setVisible(false);
            UsersManager.getCurrentUser().emailProperty().bindBidirectional(intermediaryEmailProperty);
            UsersManager.getCurrentUser().emailProperty().unbind();
        }
    }
}
