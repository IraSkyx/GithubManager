package business_logic.persistance.stub;

import business_logic.gateways.APIManager;
import business_logic.persistance.DataManager;
import business_logic.repository.Category;
import business_logic.repository.Repository;
import business_logic.user.IUser;
import business_logic.user.UserFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<IUser> loadUsers() {

        List<IUser> allUsers = new ArrayList<>(Arrays.asList(
            UserFactory.create("Adrien Lenoir", "adrien.lenoir@etu.uca.fr", "mdp"),
            UserFactory.create("Fabien Simonet", "fabien.simonet@etu.uca.fr", "mdp")
        ));

        for(IUser user : allUsers){

            Category categ = new Category("Future projects");
            Category categ2 = new Category("TO DO LIST");

            user.getUserFollow().addFollow(categ);
            user.getUserFollow().addFollow(categ2);
            
            new Thread(() -> apiManager.getRepositoriesByUsername("IraSkyx").forEach((repo) -> categ.addFollow(repo))).start();
            new Thread(() -> apiManager.getRepositoriesByUsername("ElRaffray").forEach((repo) -> categ2.addFollow(repo))).start();
            new Thread(() -> apiManager.getRepositoriesByUsername("GabinSalabert").forEach((repo) -> user.getUserFollow().addFollow(repo))).start();
        }
        return allUsers;
    }

    @Override
    public void saveUsers(List<IUser> user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
