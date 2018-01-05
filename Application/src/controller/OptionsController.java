package controller;

import business_logic.gateways.GitHubGateway;
import business_logic.user.UsersManager;
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
    @FXML Label noError;
    
    StringProperty intermediaryEmailProperty = new SimpleStringProperty(UsersManager.getCurrentUser().getEmail());
    StringProperty intermediaryUsernameProperty = new SimpleStringProperty(UsersManager.getCurrentUser().getUsername());
    StringProperty intermediaryPasswordProperty = new SimpleStringProperty(UsersManager.getCurrentUser().getPassword());
    
    /**
     * Initializes the controller class.
     */   
    public void initialize() {
        bindUsername();
        bindPassword();
        bindEmail();
    }    
    
    /**
     * Go to the OnlineMode page
     */
    @FXML private void GoOnlineMode() {FrontController.setScene("/ihm/OnlineMode.fxml", new GitHubGateway());} 
    
    /**
     * Create a bidirectional binding which allow the current user to update his username
     */
    private void bindUsername() {
        username.textProperty().bindBidirectional(intermediaryUsernameProperty);
    }
    
    /**
     * Create a bidirectional binding which allow the current user to update his password
     */
    private void bindPassword() {
        password.textProperty().bindBidirectional(intermediaryPasswordProperty);
    }
     
    /**
     * Create a bidirectional binding which allow the current user to update his email in an intermediary property
     * This property is then checked in an other method before updating the value of the current user email property
     */
    private void bindEmail() {
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
    
        @FXML
    private void submitUserChanges() {
        if(UsersManager.exists(email.textProperty().get()) && !email.textProperty().get().equals(UsersManager.getCurrentUser().getEmail())) {
            error.setVisible(true); 
            noError.setVisible(false);
            email.textProperty().set(UsersManager.getCurrentUser().getEmail());
            username.textProperty().set(UsersManager.getCurrentUser().getUsername());
            password.textProperty().set(UsersManager.getCurrentUser().getPassword());
        }
        else { 
            error.setVisible(false);
            noError.setVisible(true);
            UsersManager.getCurrentUser().emailProperty().bindBidirectional(intermediaryEmailProperty);
            UsersManager.getCurrentUser().emailProperty().unbind();
            UsersManager.getCurrentUser().usernameProperty().bindBidirectional(intermediaryUsernameProperty);
            UsersManager.getCurrentUser().usernameProperty().unbind();
            UsersManager.getCurrentUser().passwordProperty().bindBidirectional(intermediaryPasswordProperty);
            UsersManager.getCurrentUser().passwordProperty().unbind();
        }
    }
}
