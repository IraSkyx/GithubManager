package controller;

import business_logic.repository.Category;
import business_logic.user.UsersManager;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class : RenameCategoryController : Controller of RenameCategory.fxml
 *
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class RenameCategoryController extends VBox { 

    @FXML VBox rootPane; 
    @FXML TextField name;   
    @FXML Label error;
    
    private Category oldValue;
    private final PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));     

    void setOldValue(Category oldValue) {
        this.oldValue = oldValue;
        name.setText(oldValue.getName());
    }
    
    @FXML
    private void submit() {
        if(!name.getText().isEmpty() && !((Category)UsersManager.currentUserProperty().get().userFollowProperty().get()).contains(x -> x.getName().equals(name.getText()))){
            oldValue.nameProperty().setValue(name.getText());    
            ((Stage)rootPane.getScene().getWindow()).close();            
        }
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
