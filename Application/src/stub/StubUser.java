/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business_logic.APIGateway;
import business_logic.Category;
import business_logic.Repository;
import business_logic.RepositoryMaker;
import business_logic.User;
import business_logic.UsersManager;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author adlenoir
 */
public class StubUser {
    public static ArrayList<User> loadUsers(){
        ArrayList<User> allUsers = new ArrayList<>(Arrays.asList(
            new User("Adrien", "Lenoir", "adrien.lenoir@etu.uca.fr"),
            new User("Fabien", "Simonet", "fabien.simonet@etu.uca.fr")    
        ));
        loadFollows(allUsers);
        return allUsers;
    }
    
    public static void loadFollows(ArrayList<User> allUsers){
        for(User user : allUsers){
            Category categ = new Category("Future projects");
            for(Repository repo : APIGateway.getRepositories("IraSkyx"))
                categ.AddFollow(repo);
            user.getFollows().AddFollow(categ);
            
            Category categ2 = new Category("TODOLIST");
            for(Repository repo : APIGateway.getRepositories("ElRaffray"))
                categ2.AddFollow(repo);
            user.getFollows().AddFollow(categ2);
            
            for(Repository repo : APIGateway.getRepositories("GabinSalabert"))
                user.getFollows().AddFollow(repo);
        }
    }
}
