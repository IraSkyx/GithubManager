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
    
    public void setProgress(double value){
        progress.setProgress(value);
    }
}
