package controller;

import business_logic.user.IUser;
import business_logic.user.UserFactory;
import business_logic.user.UsersManager;
import java.io.IOException;
import java.security.InvalidParameterException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author fsimo
 */

public class SignUpController {
    
    @FXML TextField username;
    
    @FXML TextField email;
    
    @FXML TextField password;
    
    @FXML TextField verifpassword;
    
    @FXML Label error;
    
    @FXML 
    private void GoToHome() throws IOException {   
        FrontController.setScene("/ihm/Home.fxml");
    }
    
    @FXML 
    private void SignUp() throws IOException {  
        try {      
            IUser newUser = UserFactory.make(username.getText(), email.getText(), password.getText(), verifpassword.getText());
            UsersManager.connect(newUser.getEmail(), newUser.getPassword());
            FrontController.setScene("/ihm/OnlineMode.fxml");
        }
        catch(InvalidParameterException ex) {
            error.setText(ex.getMessage());
            error.setVisible(true);
        }
    }
}
