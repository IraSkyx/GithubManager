package business_logic.persistance.stub;

import business_logic.gateways.APIManager;
import business_logic.gateways.GitHubGateway;
import business_logic.persistance.DataManager;
import business_logic.repository.Category;
import business_logic.repository.Repository;
import business_logic.user.IUser;
import business_logic.user.UserFactory;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * StubUsers : Initialisation of the datas which refers to Users
 * @author adlenoir
 */
public class StubUsers implements DataManager {
    
    private APIManager apiManager;

    public StubUsers(APIManager apiManager) {
        this.apiManager = apiManager;
    }
    
    public void setApiManager(APIManager apiManager) {
        this.apiManager = apiManager;
    }
    
    @Override
    public ArrayList<IUser> loadUsers() {
        
        ArrayList<IUser> allUsers = new ArrayList<>(Arrays.asList(
            UserFactory.make("Adrien Lenoir", "adrien.lenoir@etu.uca.fr", "mdp"),
            UserFactory.make("Fabien Simonet", "fabien.simonet@etu.uca.fr", "mdp")
        ));
        
        //new Thread(() -> {
            for(IUser user : allUsers){
            Category categ = new Category("Future projects");
            for(Repository repo : apiManager.getRepositoriesByUsername("IraSkyx"))
                categ.addFollow(repo);
            user.getUserFollow().addFollow(categ);
            
            Category categ2 = new Category("TODOLIST");
            for(Repository repo : apiManager.getRepositoriesByUsername("ElRaffray"))
                categ2.addFollow(repo);
            user.getUserFollow().addFollow(categ2);
            
            for(Repository repo : apiManager.getRepositoriesByUsername("GabinSalabert"))
                user.getUserFollow().addFollow(repo);
            }
        //}).start();
        
        return allUsers;
    }

    @Override
    public void saveUsers(ArrayList<IUser> user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}