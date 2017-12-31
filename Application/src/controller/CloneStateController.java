package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class CloneStateController {  
    @FXML ProgressBar progress;
    
    public void setProgress(double value){
        progress.setProgress(value);
    }
}
