package controller;

import business_logic.gateways.GitHubGateway;
import business_logic.user.UsersManager;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * FXML Controller class : SignInController : Controller of SignIn.fxml
 *
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class SignInController extends BorderPane {
    
    @FXML BorderPane rootPane;
    @FXML TextField email;    
    @FXML PasswordField password;   
    @FXML Label error;
    
    private final PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
    
    @FXML private void goHome() {FrontController.setContentStage(FrontController.getStage(),"/ihm/Home.fxml");}   
    
    @FXML
    private void submit() {
        if(UsersManager.connect(email.getText(), password.getText()))
            FrontController.setScene("/ihm/OnlineMode.fxml", new GitHubGateway());
        else {
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
