/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        //HttpGet httpGet = new HttpGet(this.remoteUrl);
        //HttpResponse response = httpClient.execute(httpGet);
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
        
        TreeViewFollows.getSelectionModel().selectedItemProperty().addListener(x -> {
            selectedFollowProperty().unbind();
            selectedFollowProperty().setValue(TreeViewFollows.getSelectionModel().selectedItemProperty().getValue().getValue());           
        });
        
        searchResults.getSelectionModel().selectedItemProperty().addListener(x -> {
            selectedFollowProperty().bind(searchResults.getSelectionModel().selectedItemProperty());           
        });
        
        
        
        
        
        
        
        
        
        
        searchResults.setOnDragDetected(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                
                /* allow any transfer mode */
                Dragboard db = searchResults.startDragAndDrop(TransferMode.ANY);
                
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(searchResults.getSelectionModel().getSelectedItem().toString());
                db.setContent(content);
                
                event.consume();
            }
        });

        /*TreeViewFollows.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // data is dragged over the target
                System.out.println("onDragOver");
                
                //accept it only if it is  not dragged from the same node 
                  //and if it has a string data 
                if (event.getGestureSource() != TreeViewFollows &&
                        event.getDragboard().hasString()) {
                    // allow for both copying and moving, whatever user chooses
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                
                event.consume();
            }
        });

        TreeViewFollows.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // the drag-and-drop gesture entered the target
                System.out.println("onDragEntered");
                // show to the user that it is an actual gesture target
                if (event.getGestureSource() != TreeViewFollows &&
                        event.getDragboard().hasString()) {
                    //TreeViewFollows.setFill(Color.GREEN);
                }
                
                event.consume();
            }
        });

        TreeViewFollows.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // mouse moved away, remove the graphical cues
                //TreeViewFollows.setFill(Color.BLACK);
                
                event.consume();
            }
        });
        
        TreeViewFollows.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // data dropped
                System.out.println("onDragDropped");
                // if there is a string data on dragboard, read it and use it 
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    //TreeViewFollows.setText(db.getString());
                    success = true;
                }
                // let the source know whether the string was successfully 
                // transferred and used
                event.setDropCompleted(success);
                
                event.consume();
            }
        });

        searchResults.setOnDragDone(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // the drag-and-drop gesture ended
                System.out.println("onDragDone");
                // if the data was successfully moved, clear it
                if (event.getTransferMode() == TransferMode.MOVE) {
                    //searchResults.setText("");
                }
                
                event.consume();
            }
        });*/
        
        
        
        
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
