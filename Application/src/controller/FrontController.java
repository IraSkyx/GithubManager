package controller;

import business_logic.gateways.APIManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class : FrontController : Control of scene and stage creation
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class FrontController {
    
    private static final String CSSPATH = "/ihm/application.css";
    
    private static Stage stage;
        /**
         * Set a Stage
         * 
         * @param stage to be set
         */
        public static void setStage(Stage stage) {FrontController.stage = stage;}   
        /**
         * Get a Stage
         * 
         * @return the Stage
         */
        public static Stage getStage() {return stage;}
    
    /**
     * Get a scene
     * 
     * @return a scene
     */
    public static Scene getScene(){
        return stage.getScene();
    }      
    
    /**
     * Initialize a Stage
     * 
     * @param stage the stage to initialize
     * @param path the path of the FXML page
     * @return a FMXLLoader
     */
    public static FXMLLoader setContentStage(Stage stage, String path) {
        Image applicationIcon = new Image("/resources/imgs/Icon.png");
        stage.getIcons().add(applicationIcon);      
        FXMLLoader loader = setScene(stage,path);
        stage.show();
        return loader;
    }
    
    /**
     * Set scene in a stage
     * 
     * @param myStage the stage to add set a scene
     * @param path the path of the FXML page
     * @return a FXMLLoader 
     */
    public static FXMLLoader setScene(Stage myStage, String path) {
        FXMLLoader loader = new FXMLLoader(FrontController.class.getClass().getResource(path));          
        try {
            Scene scene = new Scene(loader.load(), myStage.getHeight(), myStage.getWidth());
            scene.getStylesheets().add(FrontController.class.getResource(CSSPATH).toExternalForm());
            myStage.setScene(scene);
        } 
        catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }             
        return loader;
    }
    
    /**
     * Set scene in a stage and fix an APIManager
     * 
     * @param path the path of the FXML page
     * @param apiManager the APIManager
     * @return a FXMLLoader
     */
    public static FXMLLoader setScene(String path, APIManager apiManager) {
        FXMLLoader loader = new FXMLLoader(FrontController.class.getClass().getResource(path));        
        try {
            Scene scene = new Scene((BorderPane)loader.load(), stage.getHeight(), stage.getWidth());
            scene.getStylesheets().add(FrontController.class.getResource(CSSPATH).toExternalForm());
            ((Manageable)loader.getController()).setApiManager(apiManager);            
            stage.setScene(scene);  
        } 
        catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return loader;
    }
}
    