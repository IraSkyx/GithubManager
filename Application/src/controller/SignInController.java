/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business_logic.user.UsersManager;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class SignInController {
    
    @FXML TextField email;
    
    @FXML PasswordField password;
    
    @FXML Label error;
    
    @FXML
    private void SignIn() throws IOException {
        if(UsersManager.connect(email.getText(), password.getText()))
            FrontController.setScene("/ihm/OnlineMode.fxml");
        else
            error.setVisible(true);
    }

    @FXML 
    private void GoToHome() throws IOException {   
        FrontController.setScene("/ihm/Home.fxml");
    }   
    
    public void initialize() {               
        /*FrontController.getScene().setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.ENTER)
                try {
                    SignIn();
                } 
                catch (IOException ex) {
                    Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });*/
    }       
}
