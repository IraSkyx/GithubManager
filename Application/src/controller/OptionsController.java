package controller;

import business_logic.gateways.GitHubGateway;
import business_logic.user.UserFactory;
import business_logic.user.UsersManager;
import java.security.InvalidParameterException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * FXML Controller class : OptionsController : Controller of Options.fxml
 *
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class OptionsController extends BorderPane {
    
    @FXML BorderPane rootPane;
    @FXML TextField username;
    @FXML TextField email;
    @FXML TextField password;
    @FXML Label error;
    
    private final PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
    
    /**
     * Go to the OnlineMode page
     */
    @FXML private void goOnlineMode() {FrontController.setScene("/ihm/OnlineMode.fxml", new GitHubGateway());} 
    
    /**
     * Submit changes
     */
    @FXML 
    private void submit() {
        try {
            if(!email.getText().equals(UsersManager.getCurrentUser().getEmail()))
                UsersManager.setCurrentUser(UserFactory.create(username.getText(), email.getText(), password.getText()));
            else {
                if(username.getText().length() < 5 || password.getText().length() < 5)
                    throw new InvalidParameterException("Username or password too short (at least 5)");
                UsersManager.getCurrentUser().setUsername(username.getText());
                UsersManager.getCurrentUser().setPassword(password.getText());
            }
            goOnlineMode();
        }
        catch(InvalidParameterException ex) {
            error.setText(ex.getMessage());
            error.setVisible(true);
            visiblePause.play();
        }
    }
    
    /**
     * Initializes the controller class.
     */   
    public void initialize() {
        visiblePause.setOnFinished(e -> error.setVisible(false));
        rootPane.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.ENTER)
                submit();
        });
        username.setText(UsersManager.getCurrentUser().getUsername());
        email.setText(UsersManager.getCurrentUser().getEmail());
        password.setText(UsersManager.getCurrentUser().getPassword());
    }
}
