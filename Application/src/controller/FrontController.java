package controller;

import business_logic.gateways.APIManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author Adrien
 */
public class FrontController {
    
    private static Stage stage;
    private static final String CSSPATH = "/ihm/application.css";
    
    public static void setStage(Stage primaryStage, String path) throws IOException{
        stage = primaryStage;
        stage.setMaximized(true);
        stage.setTitle("GithubManager");
        setScene(path);
        stage.show();
    }
    
    public static Scene getScene(){
        return stage.getScene();
    }
    
    public static void setScene(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(FrontController.class.getClass().getResource(path));  
        
        Scene scene = new Scene(loader.load(), stage.getHeight(), stage.getWidth());      
        
        scene.getStylesheets().add(FrontController.class.getResource(CSSPATH).toExternalForm());
        stage.setScene(scene);
    }
    
    public static FXMLLoader setScene(String path, APIManager apiManager) throws IOException {
        FXMLLoader loader = new FXMLLoader(FrontController.class.getClass().getResource(path));  
        
        Scene scene = new Scene((BorderPane)loader.load(), stage.getHeight(), stage.getWidth());

        ((Manageable)loader.getController()).setApiManager(apiManager);       

        scene.getStylesheets().add(FrontController.class.getResource(CSSPATH).toExternalForm());
        stage.setScene(scene);  
        
        return loader;
    }
}
