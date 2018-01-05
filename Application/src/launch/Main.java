package launch;

import business_logic.gateways.GitHubGateway;
import business_logic.persistance.stub.StubUsers;
import business_logic.persistance.xml.XMLDataManager;
import business_logic.user.UsersManager;
import controller.FrontController;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Launch class : Main : Launch the application
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class Main extends Application {
        
    @Override
    public void start(Stage stage) {
        UsersManager.setDataManager(new XMLDataManager());         
        UsersManager.loadUsers();     

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        stage.setWidth(visualBounds.getWidth()*0.8);
        stage.setHeight(visualBounds.getHeight()*0.8);
        stage.centerOnScreen();
        stage.setTitle("GithubManager");

        FrontController.setStage(stage);
        FrontController.setContentStage(stage,"/ihm/Home.fxml");      

        //UsersManager.setCurrentUser(UsersManager.getAllUsers().get(0));            
    }

    public static void main(String[] args) {           
        launch(args);
    }
    
    @Override
    public void stop() {
        UsersManager.setDataManager(new XMLDataManager());
        UsersManager.saveUsers();
    }
}
