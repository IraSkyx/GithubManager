package controller;

import business_logic.cellFactory.ComboBoxChoiceFactory;
import business_logic.gateways.GitHubGateway;
import business_logic.user.UsersManager;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class : AddCategoryController : Controller of Home.fxml
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class HomeController extends BorderPane {
      
    @FXML TextField input;
    @FXML ComboBox searchBy;
    @FXML Button signIn;
    @FXML Button signUp;
    @FXML Button logOff;
    @FXML Label username; 
    
    @FXML private void goSignIn() {FrontController.setScene(FrontController.getStage(),"/ihm/SignIn.fxml");}
    
    @FXML private void goSignUp() {FrontController.setScene(FrontController.getStage(),"/ihm/SignUp.fxml");}
    
    @FXML private void goOnline() {FrontController.setScene("/ihm/OnlineMode.fxml", new GitHubGateway());}
    
    @FXML private void logOff() {UsersManager.disconnect();}
    
    @FXML
    private void onEnter() {        
        OnlineModeController ctrl = (OnlineModeController)(FrontController.setScene("/ihm/OnlineMode.fxml", new GitHubGateway()).getController()); 
        ctrl.setItems(input.getText(),searchBy.getSelectionModel().selectedItemProperty().isNull().get() ? "" : (String)searchBy.getSelectionModel().getSelectedItem());      
        ctrl.setPlaceholderSearchResults("Results loading ...");
    }
    
    public void initialize(){
       BooleanBinding nullToBool = Bindings
            .when(UsersManager.currentUserProperty().isNull())            
            .then(true)
            .otherwise(false);
    
        BooleanBinding nullToBool2 = Bindings
            .when(UsersManager.currentUserProperty().isNull())    
            .then(false)
            .otherwise(true);
        
        searchBy.setItems(ComboBoxChoiceFactory.comboList);
        
        signIn.visibleProperty().bind(nullToBool);     
        signUp.visibleProperty().bind(nullToBool);      
        username.visibleProperty().bind(nullToBool2);     
        logOff.visibleProperty().bind(nullToBool2);   
        
        username.textProperty().bind(Bindings.format("Welcome\n%s",UsersManager.currentUserProperty())); 
    }
}
