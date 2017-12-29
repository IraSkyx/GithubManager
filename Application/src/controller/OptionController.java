/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author fsimo
 */
public class OptionController {

    /**
     * Initializes the controller class.
     */

    public void initialize() {
        // TODO
    }    
    
    @FXML
    private void GoOnlineMode() throws IOException {
        FrontController.setScene("/ihm/OnlineMode.fxml");
    } 
    
}
