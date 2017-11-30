package controller;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Adrien
 */
public class FrontController {
    private static Stage stage;
    
    public static void setStage(Stage primaryStage, BorderPane panel){
        stage = primaryStage;
        stage.setWidth(1440);
        stage.setHeight(920);
        stage.setTitle("GithubManager");
        setScene(panel);
        stage.show();
    }
    
    public static void setScene(BorderPane panel){
        Scene scene = new Scene(panel, stage.getHeight(), stage.getWidth());           
        scene.getStylesheets().add(FrontController.class.getResource("/ihm/application.css").toExternalForm());
        stage.setScene(scene);
    }
}
