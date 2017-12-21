package launch;

import business_logic.gateways.GitHubGateway;
import business_logic.persistance.stub.StubUsers;
import business_logic.user.UsersManager;
import controller.FrontController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import business_logic.persistance.xml.XMLUsers;

public class Main extends Application {
        
    @Override
    public void start(Stage stage) {
        try {  
            //UsersManager.setDataManager(new XMLUsers());
            UsersManager.setDataManager(new StubUsers(new GitHubGateway()));
            UsersManager.loadUsers();     
            
            FrontController.setStage(stage,"/ihm/Home.fxml");    
            
            UsersManager.setCurrentUser(UsersManager.getAllUsers().get(0));
            //UsersManager.setDataManager(new XMLUsers(new GitHubGateway()));
        } 
        catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {           
        launch(args);
    }
    
    @Override
    public void stop() {
        UsersManager.saveUsers();
    }
}
