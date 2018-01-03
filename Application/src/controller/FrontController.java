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
 * 
 * @author Adrien
 */
public class FrontController {
    
    private static final String CSSPATH = "/ihm/application.css";
    
    private static Stage stage;
        public static void setStage(Stage stage) {FrontController.stage = stage;}   
        public static Stage getStage() {return stage;}
    
    public static Scene getScene(){
        return stage.getScene();
    }
        
    public static FXMLLoader setContentStage(Stage stage, String path) {
        Image applicationIcon = new Image("/resources/imgs/Icon.png");
        stage.getIcons().add(applicationIcon);      
        FXMLLoader loader = setScene(stage,path);
        stage.show();
        return loader;
    }
    
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
    