package controller;

import business_logic.gateways.GitHubGateway;
import business_logic.user.IUser;
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
 *
 * @author fsimo
 */

public class SignUpController extends BorderPane {
    
    @FXML BorderPane rootPane;
    
    @FXML TextField username;    
    @FXML TextField email;   
    @FXML TextField password;    
    @FXML TextField verifpassword;
    
    @FXML Label error;
    
    private final PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
    
    @FXML private void goHome() {FrontController.setContentStage(FrontController.getStage(),"/ihm/Home.fxml");}
    
    @FXML 
    private void submit() {  
        try {      
            IUser newUser = UserFactory.create(username.getText(), email.getText(), password.getText(), verifpassword.getText());
            UsersManager.connect(newUser.getEmail(), newUser.getPassword());
            FrontController.setScene("/ihm/OnlineMode.fxml", new GitHubGateway());
        }
        catch(InvalidParameterException ex) {
            error.setText(ex.getMessage());
            error.setVisible(true);
            visiblePause.play();
        }
    }
    
    public void initialize() {  
        visiblePause.setOnFinished(e -> error.setVisible(false));
        rootPane.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.ENTER)
                submit();
        });
    } 
}
