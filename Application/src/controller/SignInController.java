/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business_logic.ModelGateway;
import business_logic.UsersManager;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
        if(ModelGateway.connect(email.getText(), password.getText()))
            FrontController.setScene((BorderPane)FXMLLoader.load(getClass().getResource("/ihm/OnlineMode.fxml")));
        else
            error.setVisible(true);
    }

    @FXML 
    private void GoToHome() throws IOException {   
        FrontController.setScene((BorderPane)FXMLLoader.load(getClass().getResource("/ihm/Home.fxml")));
    }   
}
