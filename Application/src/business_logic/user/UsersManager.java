package business_logic.user;

import business_logic.persistance.DataManager;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Adrien
 */
public class UsersManager {
    
    private static DataManager dataManager;
    private static ArrayList<IUser> allUsers = new ArrayList<>();
    
    private static final ObjectProperty<IUser> currentUser = new SimpleObjectProperty();
        public static IUser getCurrentUser() {return currentUser.get();}
        public static void setCurrentUser(IUser user) {currentUser.set(user);}
        public static ObjectProperty<IUser> currentUserProperty(){return currentUser;} 
    
    /**
     * Set the DataManager
     * 
     * @param dm DataManager
     */
    public static void setDataManager(DataManager dm) {
        dataManager = dm;
    }
    
    /**
     * Call the loading method of the DataManager
     */
    public static void loadUsers() {
        if (dataManager != null) {
           dataManager.loadUsers().stream().forEach(x -> allUsers.add(x));
        }
    }  
    
    /**
     * Call the saving method of the DataManager
     */
    public static void saveUsers() {
        if (dataManager != null) {
            dataManager.saveUsers(allUsers);
        }
    }
    
    /**
     * Getter of allUsers 
     * 
     * @return allUsers the list of all the users
     * 
     */      
    public static ArrayList<IUser> getAllUsers() {
        return allUsers;
    }   

    /**
     * Return a user in the list of allUsers 
     * 
     * @param email 
     * @param password 
     * 
     * @return User an instance of user or null
     * 
     */  
    public static IUser getUser(String email, String password){
        return allUsers.stream().filter(x -> x.getEmail().equals(email)).findFirst().orElse(null);
    }
    
    /**
     * Reset current User 
     */
    public static void disconnect(){
        currentUserProperty().setValue(UserFactory.make());
    }
       
    /**
     * Verify the user is valid and set the current user 
     * 
     * @param email 
     * @param password 
     * 
     * @return true if connection succeeded, false otherwise
     * 
     */ 
    public static boolean connect(String email, String password){
        IUser user = getUser(email, password);
        if(user != null){
            setCurrentUser(user);
            return true;
        }
        return false;     
    }
    
    /**
     * Verify the email exists in the list 
     * 
     * @param email 
     * 
     * @return True if yes, false otherwise
     * 
     */ 
    public static boolean exists(String email) {
        return allUsers.stream().anyMatch(x -> x.getEmail().equals(email));
    }      
}