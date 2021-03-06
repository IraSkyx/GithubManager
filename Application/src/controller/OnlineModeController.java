package controller;

import business_logic.cellFactory.ComboBoxChoiceFactory;
import business_logic.cellFactory.TreeItemFollowCell;
import business_logic.gateways.APIManager;
import business_logic.repository.Category;
import business_logic.repository.Follow;
import business_logic.repository.Repository;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import business_logic.user.UsersManager;
import converters.PropertyStringConverter;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class : OnlineModeController : Controller of OnlineMode.fxml
 *
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class OnlineModeController extends BorderPane implements Manageable {
    
    @FXML ListView searchResults;  
    
    @FXML ComboBox searchBy;
    
    @FXML TextField input;
    
    @FXML TreeView<Follow> TreeViewFollows;
    
    @FXML Label username;
    @FXML Label date;

    @FXML Button cloneBtn;
    
    @FXML TreeItem root;
 
    private APIManager apiManager;
        /**
         * Set APIManager 
         * 
         * @param apiManager APIManager to be set
         */
        @Override public void setApiManager(APIManager apiManager) {this.apiManager = apiManager;}
    
    /**
     * isLoggedIn BooleanProperty
     */
    private final BooleanProperty isLoggedIn = new SimpleBooleanProperty();   
        /**
         * Get isLoggedIn
         * 
         * @return the boolean which correspond to isLoggedIn
         */
        public final Boolean getIsLoggedIn() { return isLoggedIn.get(); }
        /**
         * set isLoggedIn
         * 
         * @param value the boolean to be set as isLoggedIn
         */
        public final void setIsLoggedIn(Boolean value) { isLoggedIn.set(value); }
        /**
         * Get isLoggedIn BooleanProperty
         * 
         * @return the BooleanProperty which correspond to isLoggedIn
         */
        public BooleanProperty isLoggedInProperty(){return isLoggedIn;}; 
        
    private final ObjectProperty<Follow> selectedFollow = new SimpleObjectProperty();   
        /**
         * Get selectedFollow
         * 
         * @return the Follow which correspond to isLoggedIn selectedFollow
         */
        public final Follow getSelectedFollow() { return selectedFollow.get(); }
        /**
         * Set selectedFollow
         * 
         * @param value the Follow to be set as selectedFollow
         */
        public final void setSelectedFollow(Follow value) { selectedFollow.set(value); }
        /**
         * Get selectedFollow ObjectProperty of Follows
         * 
         * @return the Object Property as selectedFollow
         */
        public ObjectProperty<Follow> selectedFollowProperty(){return selectedFollow;};         
     
    /**
     * Clone selected Repository URL 
     */
    @FXML
    private void cloneUrl() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure ? This may take a few minutes", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
            if(getSelectedFollow() != null && getSelectedFollow() instanceof Repository && apiManager != null)
                apiManager.cloneRepository((Repository)getSelectedFollow());       
    }
        
    @FXML private void searchFor() {setItems(input.getText(),searchBy.getSelectionModel().selectedItemProperty().isNull().get() ? "" : (String)searchBy.getSelectionModel().getSelectedItem());}
    
    /**
     * Go to Home page
     */
    @FXML private void goHome() {FrontController.setContentStage(FrontController.getStage(),"/ihm/Home.fxml");}
    
    /**
     * Go to SignIn page
     */
    @FXML private void goSignIn() {FrontController.setContentStage(FrontController.getStage(),"/ihm/SignIn.fxml");}
    
    /**
     * Go to SignUp page
     */
    @FXML private void goSignUp() {FrontController.setContentStage(FrontController.getStage(),"/ihm/SignUp.fxml");}
    
    /**
     * Go to Options page
     */
    @FXML private void goOptions() {FrontController.setContentStage(FrontController.getStage(),"/ihm/Options.fxml");}
    
    /**
     * Disconnect the current User
     */
    @FXML private void logOff() {UsersManager.disconnect();}
    
    /**
     * Delete a Follow in a TreeView component
     */
    @FXML
    private void deleteFollow() {
        if(TreeViewFollows.getSelectionModel().getSelectedItem() != TreeViewFollows.getRoot())
            TreeViewFollows.getSelectionModel().selectedItemProperty().get().getParent().getValue().deleteFollow(TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue());
    }
    
    /**
     * Go to AddCategory page and add the Category
     */
    @FXML
    private void addCategory() {      
        Follow selected;
        
        if(TreeViewFollows.getSelectionModel().getSelectedItem() != null){
            selected = TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue() instanceof Category ? TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue() : TreeViewFollows.getSelectionModel().selectedItemProperty().getValue().getParent().getValue();
        }
        else{
            selected = TreeViewFollows.getRoot().getValue();
        }
        Stage stage = new Stage(); 
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add category");                    
        ((AddCategoryController)FrontController.setContentStage(stage,"/ihm/AddCategory.fxml").getController()).setSelected(selected);                
    }
    
    /**
     * Go to renameCategory page and rename the Category
     */
    @FXML
    private void renameCategory() {             
        if(TreeViewFollows.getSelectionModel().getSelectedItem() != null && TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue() instanceof Category){          
            Stage stage = new Stage(); 
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Rename category");          
            ((RenameCategoryController)FrontController.setContentStage(stage,"/ihm/RenameCategory.fxml").getController()).setOldValue((Category)TreeViewFollows.getSelectionModel().getSelectedItem().getValue());         
        }
    }
    
    /**
     * Manage combobox choice
     * 
     * @param input item to set
     * @param choice user's research choice 
     */
    public void setItems(String input, String choice) {
        if(apiManager != null){
            Platform.runLater(() -> {
            switch(choice){
                case "By name" : searchResults.setItems(apiManager.getRepositoriesByName(input));                
                default: searchResults.setItems(apiManager.getRepositoriesByUsername(input));
            }              
            setPlaceholderSearchResults("No results for your search");
        });
        }
    }
    
    /**
     * Initialize actions like a main method
     */
    public void initialize() {

        BooleanBinding loggedIn = Bindings
            .when(UsersManager.currentUserProperty().isNull())    
            .then(false)
            .otherwise(true);

        isLoggedIn.bind(loggedIn);
        
        searchBy.setItems(ComboBoxChoiceFactory.comboList);
 
        cloneBtn.visibleProperty().bind(Bindings
            .when(selectedFollowProperty().isNull())
            .then(false)
            .otherwise(true)
        );     
        
        date.visibleProperty().bind(cloneBtn.visibleProperty());
        date.textProperty().bind(Bindings.format("Date of creation : %s", Bindings.selectString(selectedFollowProperty(),"createdAt")));
        
        if(getIsLoggedIn()) Bindings.bindBidirectional(username.textProperty(), UsersManager.currentUserProperty().get().usernameProperty(), new PropertyStringConverter());
        
        TreeViewFollows.setOnKeyPressed((KeyEvent keyEvent) -> {
            if(TreeViewFollows.getSelectionModel().getSelectedItem() != null && keyEvent.getCode().equals(KeyCode.DELETE))
                deleteFollow();
        });
        
        TreeViewFollows.getSelectionModel().selectedItemProperty().addListener(x -> {
            if(TreeViewFollows.getSelectionModel().getSelectedItem() != null && TreeViewFollows.getSelectionModel().selectedItemProperty().get().getValue() instanceof Repository){
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
            TreeItemFollowCell.firstRender = false;
        }
    }   
    
    /**
     * Wrap the search result placeholder in a label
     * 
     * @param text text to insert in the label
    */
    public void setPlaceholderSearchResults(String text) {
        Label placeholder = new Label(text);
        placeholder.setPadding(new Insets(20,20,20,20));   
        searchResults.setPlaceholder(placeholder);
    }
    
    /**
     * Update a TreeView 
     * 
     * @param root the TreeItem to update
     */
    public void updateTreeView(TreeItem<Follow> root) {
        ((Category)root.getValue()).getListOfFollows().stream().forEach(x -> {           
            TreeItem<Follow> item = new TreeItem<>(x);     
            if(x instanceof Category)
                updateTreeView(item);
            root.getChildren().add(item);
        });
    }

    /**
     * Add a listener on a Category
     * 
     * @param categ the category on whitch we want to add a listener
     */
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
}
