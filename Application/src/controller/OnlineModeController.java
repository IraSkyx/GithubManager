package controller;

import business_logic.gateways.APIManager;
import business_logic.repository.Category;
import business_logic.repository.Follow;
import business_logic.user.UserFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import business_logic.user.UsersManager;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class OnlineModeController extends BorderPane implements Manageable {
    
    @FXML ListView searchResults;  
    
    @FXML TextField input;
    
    @FXML TreeView<Follow> TreeViewFollows;
    
    @FXML Label h31;
    
    @FXML Button loggedOff1;
    
    @FXML Button loggedOff2;
    
    @FXML Label loggedIn1;
    
    @FXML Button loggedIn2;
    
    @FXML Button download;
    
    @FXML TreeItem root;
    
    private APIManager apiManager;
    
    private final ObjectProperty<Follow> selectedFollow = new SimpleObjectProperty();   
        public final Follow getSelectedFollow() { return selectedFollow.get(); }
        public final void setSelectedFollow(Follow value) { selectedFollow.set(value); }
        public ObjectProperty<Follow> selectedFollowProperty(){return selectedFollow;};       
    
    @Override
    public void setApiManager(APIManager apiManager) {
        this.apiManager = apiManager;
    }
        
    @FXML
    private void cloneUrl() {
        updateTreeView(root);
    }
        
    @FXML
    private void onEnter() {   
        Platform.runLater(() -> {
            setItems(input.getText());
        });     
    }
    
    @FXML
    private void GoHome() throws IOException{
        FrontController.setScene("/ihm/Home.fxml");
    }
    
    @FXML
    private void SignIn() throws IOException{
        FrontController.setScene("/ihm/SignIn.fxml");
    }
    
    @FXML
    private void SignUp() throws IOException{
        FrontController.setScene("/ihm/SignUp.fxml");
    }
    
    @FXML
    private void logOff() throws IOException{
        UsersManager.disconnect();       
    }
    
    public void setItems(String input) {
        searchResults.setItems(apiManager.getRepositoriesByUsername(input));
    }
    
    public void initialize(){   
        
        BooleanBinding nullToBool = Bindings
            .when(UsersManager.currentUserProperty().isEqualTo(UserFactory.make()))            
            .then(true)
            .otherwise(false);
        
        BooleanBinding nullToBool2 = Bindings
            .when(UsersManager.currentUserProperty().isEqualTo(UserFactory.make()))    
            .then(false)
            .otherwise(true);
        
        h31.visibleProperty().bind(nullToBool2); 
        h31.managedProperty().bind(nullToBool2);        
        
        loggedIn1.visibleProperty().bind(nullToBool2);     
        loggedIn2.visibleProperty().bind(nullToBool2);           
        loggedOff1.visibleProperty().bind(nullToBool);        
        loggedOff2.visibleProperty().bind(nullToBool); 
        
        loggedIn1.textProperty().bind(Bindings.format("Welcome %s", UsersManager.currentUserProperty().asString()));
        
        loggedIn1.managedProperty().bind(nullToBool2);     
        loggedIn2.managedProperty().bind(nullToBool2);           
        loggedOff1.managedProperty().bind(nullToBool);        
        loggedOff2.managedProperty().bind(nullToBool);
        
        TreeViewFollows.visibleProperty().bind(nullToBool2);
        TreeViewFollows.managedProperty().bind(nullToBool2); 
        
        root.valueProperty().bind(UsersManager.currentUserProperty().get().userFollowProperty());
        
        updateTreeView(root);    
        TreeViewFollows.setEditable(true);
        
        TreeViewFollows.getSelectionModel().selectedItemProperty().addListener(x -> {
            selectedFollowProperty().unbind();
            selectedFollowProperty().setValue(TreeViewFollows.getSelectionModel().selectedItemProperty().getValue().getValue());           
        });
        
        searchResults.getSelectionModel().selectedItemProperty().addListener(x -> {
            selectedFollowProperty().bind(searchResults.getSelectionModel().selectedItemProperty());           
        });
    }   
    
    public void updateTreeView(TreeItem<Follow> root) {
        ((Category)root.getValue()).getListOfFollows().stream().forEach(x -> {
            
            TreeItem<Follow> item = new TreeItem<>(x);
            
            if(x instanceof Category)
                updateTreeView(item);
            root.getChildren().add(item);
        });
    }
}
