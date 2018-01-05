package controller;

import business_logic.repository.Category;
import business_logic.repository.Follow;
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
 * FXML Controller class : AddCategoryController : Controller of AddCategory.fxml
 *
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class AddCategoryController extends VBox {  
    
    @FXML VBox rootPane; 
    @FXML TextField name;
    @FXML Label error;    
    
    private Follow selected;
        /**
         * Set selected Follow
         * 
         * @param selected Follow to set
         */
        public void setSelected(Follow selected) { this.selected=selected;}
    
    private final PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
    
    /**
     * Make verifications about the Category submited by the user and add it or display an error
     */
    @FXML
    private void submit() {           
        if(!name.getText().isEmpty() && !((Category)UsersManager.currentUserProperty().get().userFollowProperty().get()).contains(x -> x.getName().equals(name.getText()))){
            selected.addFollow(new Category(name.getText()));
            ((Stage)name.getScene().getWindow()).close();
        }
        else{
            error.setVisible(true); 
            visiblePause.play();
        }        
    }  
    
    /**
     * Initialize actions like a main method
     */
    public void initialize() {         
        visiblePause.setOnFinished(e -> error.setVisible(false));      
        rootPane.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.ENTER)
                submit();
        });
    } 
}
