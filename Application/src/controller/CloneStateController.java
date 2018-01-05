package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class : CloneStateController : Controller of CloneState.fxml
 *
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class CloneStateController {  
    @FXML ProgressBar progress;
    
    /**
     * Set value to a progress bar
     * 
     * @param value 
     */
    public void setProgress(double value){
        progress.setProgress(value);
    }
}
