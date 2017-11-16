package launch;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    private static Stage primaryStage;
    
    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }
    
    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }
        
    @Override
    public void start(Stage stage) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/ihm/Home.fxml"));
            Scene scene = new Scene(root, 1080, 720);           
            scene.getStylesheets().add(getClass().getResource("/ihm/application.css").toExternalForm());
            setPrimaryStage(stage);
            primaryStage.setScene(scene);
            primaryStage.setTitle("GitHubManager");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
