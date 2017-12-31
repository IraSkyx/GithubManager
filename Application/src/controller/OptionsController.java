package controller;

import business_logic.gateways.GitHubGateway;
import business_logic.user.UsersManager;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author fsimo
 */
public class OptionsController extends BorderPane {

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
     */
    @FXML private void GoOnlineMode() {FrontController.setScene("/ihm/OnlineMode.fxml", new GitHubGateway());} 
    
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
