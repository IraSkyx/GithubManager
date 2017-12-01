package launch;

import controller.FrontController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
        
    @Override
    public void start(Stage stage) {
        try {       
            FrontController.setStage(stage, (BorderPane) FXMLLoader.load(getClass().getResource("/ihm/Home.fxml")));
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
