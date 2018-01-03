package launch;

import business_logic.gateways.GitHubGateway;
import business_logic.persistance.stub.StubUsers;
import business_logic.persistance.xml.XMLDataManager;
import business_logic.user.UsersManager;
import controller.FrontController;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
        
    @Override
    public void start(Stage stage) {
        UsersManager.setDataManager(new XMLDataManager());
        //UsersManager.setDataManager(new StubUsers(new GitHubGateway()));           
        UsersManager.loadUsers();     

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        stage.setWidth(gd.getDisplayMode().getWidth()*0.8);
        stage.setHeight(gd.getDisplayMode().getHeight()*0.8);

        stage.setTitle("GithubManager");
        FrontController.setStage(stage);
        FrontController.setContentStage(stage,"/ihm/Home.fxml");

        UsersManager.setCurrentUser(UsersManager.getAllUsers().get(0));            
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
