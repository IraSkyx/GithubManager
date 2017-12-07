/*
 * StubUser : Initialisation of the datas which refers to Users
 */
package stub;

import business_logic.APIGateway;
import business_logic.Category;
import business_logic.Repository;
import business_logic.User;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author adlenoir
 */
public class StubUser {
    public static ArrayList<User> loadUsers(){
        ArrayList<User> allUsers = new ArrayList<>(Arrays.asList(
            new User("Adrien Lenoir", "mdp", "adrien.lenoir@etu.uca.fr"),
            new User("Fabien Simonet", "mdp", "fabien.simonet@etu.uca.fr")    
        ));
        loadFollows(allUsers);
        return allUsers;
    }
    
    public static void loadFollows(ArrayList<User> allUsers){
        
        new Thread(() -> {
            for(User user : allUsers){
            Category categ = new Category("Future projects");
            for(Repository repo : APIGateway.getRepositories("IraSkyx"))
                categ.AddFollow(repo);
            user.getMyFollow().AddFollow(categ);
            
            Category categ2 = new Category("TODOLIST");
            for(Repository repo : APIGateway.getRepositories("ElRaffray"))
                categ2.AddFollow(repo);
            user.getMyFollow().AddFollow(categ2);
            
            for(Repository repo : APIGateway.getRepositories("GabinSalabert"))
                user.getMyFollow().AddFollow(repo);
            }
        }).start();
        
        
    }
}
