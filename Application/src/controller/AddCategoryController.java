package controller;

import business_logic.repository.Category;
import business_logic.repository.CategoryFactory;
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
 * FXML Controller class
 *
 * @author Adrien
 */
public class AddCategoryController extends VBox {  
    
    @FXML VBox rootPane; 
    @FXML TextField name;
    @FXML Label error;    
    
    private Follow selected;
        public void setSelected(Follow selected) { this.selected=selected;}
    
    private final PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
    
    @FXML
    private void submit() {           
        if(!name.getText().isEmpty() && !((Category)UsersManager.currentUserProperty().get().userFollowProperty().get()).contains(name.getText())){
            selected.addFollow(CategoryFactory.create(name.getText()));
            ((Stage)name.getScene().getWindow()).close();
        }
        else{
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
