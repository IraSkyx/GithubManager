package controller;

import business_logic.cellFactory.TreeItemFollowCell;
import business_logic.gateways.APIManager;
import business_logic.gateways.GitHubGateway;
import business_logic.repository.Category;
import business_logic.repository.Follow;
import business_logic.repository.Repository;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import business_logic.user.UsersManager;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML Label loggedIn1;
    @FXML Label readMe;
    
    @FXML Button loggedOff1;    
    @FXML Button loggedOff2;   
    @FXML Button loggedIn2; 
    @FXML Button TVMenu1; 
    @FXML Button TVMenu2; 
    @FXML Button TVMenu3; 
    @FXML Button cloneBtn;
    
    @FXML TreeItem root;
    
    BooleanBinding nullToBool = Bindings
            .when(UsersManager.currentUserProperty().isNull())            
            .then(true)
            .otherwise(false);
    
    BooleanBinding nullToBool2 = Bindings
            .when(UsersManager.currentUserProperty().isNull())    
            .then(false)
            .otherwise(true);
    
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
        try {
            if(getSelectedFollow() != null)
                GitHubGateway.cloneRepository((Repository)getSelectedFollow());
        } 
        catch (IOException ex) {
            Logger.getLogger(OnlineModeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @FXML
    private void onEnter() {   
        setItems(input.getText());        
    }
    
    @FXML
    private void GoHome() throws IOException {
        FrontController.setScene("/ihm/Home.fxml");
    }
    
    @FXML
    private void SignIn() throws IOException {
        FrontController.setScene("/ihm/SignIn.fxml");
    }
    
    @FXML
    private void SignUp() throws IOException {
        FrontController.setScene("/ihm/SignUp.fxml");
    }
    
    @FXML
    private void logOff() throws IOException {
        UsersManager.disconnect();       
    }
    
    @FXML
    private void deleteFollow() throws IOException {
        if(TreeViewFollows.getSelectionModel().getSelectedItem() != TreeViewFollows.getRoot())
            TreeViewFollows.getSelectionModel().selectedItemProperty().get().getParent().getValue().deleteFollow(TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue());
    }
    
    public void setItems(String input) {      
        Platform.runLater(() -> {
            searchResults.setItems(apiManager.getRepositoriesByUsername(input));
            setPlaceholderSearchResults("No results for your search");
        });
    }
    
    public void initialize(){        
        h31.visibleProperty().bind(nullToBool2); 
        h31.managedProperty().bind(nullToBool2);  
        cloneBtn.visibleProperty().bind(Bindings
                .when(selectedFollowProperty().isNull())
                .then(false)
                .otherwise(true)
        );
        
        loggedIn1.visibleProperty().bind(nullToBool2);     
        loggedIn2.visibleProperty().bind(nullToBool2);
        TVMenu1.visibleProperty().bind(nullToBool2);  
        TVMenu2.visibleProperty().bind(nullToBool2);  
        TVMenu3.visibleProperty().bind(nullToBool2);  
        loggedOff1.visibleProperty().bind(nullToBool);        
        loggedOff2.visibleProperty().bind(nullToBool); 
        
        loggedIn1.textProperty().bind(Bindings.format("Welcome\n%s", UsersManager.currentUserProperty().asString()));
        
        loggedIn1.managedProperty().bind(nullToBool2);     
        loggedIn2.managedProperty().bind(nullToBool2);  
        TVMenu1.managedProperty().bind(nullToBool2);  
        TVMenu2.managedProperty().bind(nullToBool2);  
        TVMenu3.managedProperty().bind(nullToBool2); 
        loggedOff1.managedProperty().bind(nullToBool);        
        loggedOff2.managedProperty().bind(nullToBool);
        
        TreeViewFollows.visibleProperty().bind(nullToBool2);
        TreeViewFollows.managedProperty().bind(nullToBool2);       
        
        TreeViewFollows.setOnKeyPressed((KeyEvent keyEvent) -> {
            if (TreeViewFollows.getSelectionModel().getSelectedItem() != null && keyEvent.getCode().equals(KeyCode.DELETE )){
                try {
                    deleteFollow();
                }
                catch (IOException ex) {
                  Logger.getLogger(OnlineModeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        TreeViewFollows.getSelectionModel().selectedItemProperty().addListener(x -> {
           if(TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue() instanceof Repository){
                selectedFollowProperty().unbind();
                selectedFollowProperty().setValue(TreeViewFollows.getSelectionModel().getSelectedItem().getValue());
            } 
        });
        
        searchResults.getSelectionModel().selectedItemProperty().addListener(x -> {
            selectedFollowProperty().bind(searchResults.getSelectionModel().selectedItemProperty());           
        });
        
        setPlaceholderSearchResults("No result for your search");
        
        if(UsersManager.currentUserProperty().isNotNull().get()){
            root.valueProperty().bind(UsersManager.currentUserProperty().get().userFollowProperty());
            initializeListeners((Category)UsersManager.currentUserProperty().getValue().userFollowProperty().getValue());
            updateTreeView(root);
            TreeItemFollowCell.firstRender=false;
        }
    }   
    
    public void setPlaceholderSearchResults(String text) {
        Label placeholder = new Label(text);
        placeholder.setPadding(new Insets(20,20,20,20));   
        searchResults.setPlaceholder(placeholder);
    }
    
    public void updateTreeView(TreeItem<Follow> root) {
        ((Category)root.getValue()).getListOfFollows().stream().forEach(x -> {           
            TreeItem<Follow> item = new TreeItem<>(x);     
            if(x instanceof Category)
                updateTreeView(item);
            root.getChildren().add(item);
        });
    }

    private void initializeListeners(Category categ) {
        categ.getListOfFollows().addListener(new ListChangeListener(){       
            @Override
            public void onChanged(ListChangeListener.Change c) {               
                Platform.runLater(() -> {
                    while (c.next())
                        if (c.wasAdded())
                            for(Follow follow : (List<Follow>)c.getAddedSubList())
                                if(follow instanceof Category)
                                    initializeListeners((Category)follow);             
                    TreeViewFollows.getRoot().getChildren().clear();
                    updateTreeView(root);
                });            
            }
        });
        for(Follow follow : categ.getListOfFollows()){
            if(follow instanceof Category)
                initializeListeners((Category)follow);
        }
    } 
        
    @FXML
    private void renameCategory() throws IOException {             
        if(TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue() instanceof Category){
            
            Category oldValue = (Category)TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue();
            
            Stage stage = new Stage();
            stage.setWidth(300);
            stage.setHeight(150);
            stage.initModality(Modality.APPLICATION_MODAL);

            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(FrontController.class.getResource("/ihm/application.css").toExternalForm());
            TextField tf = new TextField();           
            Button submit = new Button("Submit");
            
            tf.textProperty().setValue(oldValue.nameProperty().get());          
            submit.setOnAction(e -> {
                oldValue.nameProperty().setValue(tf.getText());
                stage.close();
            });

            root.getChildren().addAll(tf, submit);
            stage.setScene(scene);
            stage.showAndWait();
        }
    }
    
    @FXML
    private void addCategory(){
        
        Stage stage = new Stage();
            stage.setWidth(300);
            stage.setHeight(150);
            stage.initModality(Modality.APPLICATION_MODAL);

            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(FrontController.class.getResource("/ihm/application.css").toExternalForm());
            TextField tf = new TextField();      
            Label error = new Label("Category of the same name already exists");
            error.setVisible(false);
            error.getStyleClass().add("error");
            Button submit = new Button("Submit");
                        
            submit.setOnAction(e -> {
                if(((Category)UsersManager.currentUserProperty().get().userFollowProperty().get()).contains(tf.getText())){
                    error.setVisible(true);
                    return;
                }                   
                try {
                    Follow selected = TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue();    
                    TreeViewFollows.getSelectionModel().selectedItemProperty().getValue().getParent().getValue().addFollow(new Category(tf.getText()));
                }
                catch(NullPointerException ex){
                    TreeViewFollows.getRoot().getValue().addFollow(new Category(tf.getText()));
                }
                stage.close();
            });

            root.getChildren().addAll(tf,error,submit);
            stage.setScene(scene);
            stage.showAndWait();      
    }
}
