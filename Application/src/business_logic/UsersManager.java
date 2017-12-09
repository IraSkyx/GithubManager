/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Adrien
 */
public class UsersManager {
   
    private static ArrayList<User> allUsers = new ArrayList<>();
    
    private static final ObjectProperty<User> currentUser = new SimpleObjectProperty();
        public static User getCurrentUser() {return currentUser.get();}
        public static void setCurrentUser(User user) {currentUser.set(user);}
        public static ObjectProperty<User> currentUserProperty(){return currentUser;} 

        
    private DataManager dataManager;
    
    /**
     * instantiate a data manager
     * 
     * @param dm data manager
     */
    public void setDataManager(DataManager dm) {
        dataManager = dm;
    }
    
    /**
     * the manager recover the saved users and load them in a list of users calling the data manager
     * (it is the data manager which has the responsability to load)
     * 
     */
    public void loadUsers() {
        if (dataManager != null) {
            ArrayList<User> allUsers = dataManager.loadUsers();
        }
    }  
    
    /**
     * the manager saves users calling the data manager 
     * (it is the data manager which has the responsability to save)
     *     
     */
    public void saveUsers() {
        if (dataManager != null) {
            dataManager.saveUsers(allUsers);
        }
    }
    
    /**
     * getter of allUsers 
     * 
     * @return allUsers the list of all the users
     * 
     */      
    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }
    
    /**
     * setter of allUsers
     * 
     * @param allUsers the list of all the users
     * 
     */       
    public static void setAllUsers(ArrayList<User> allUsers) {
        UsersManager.allUsers = allUsers;
    }

    /**
     * return a user in the list of allUsers 
     * 
     * @param email 
     * @param password 
     * 
     * @return User an instance of user or null
     * 
     */  
    public static User getUser(String email, String password){
        return allUsers.stream().filter(x -> x.getEmail().equals(email)).findFirst().orElse(null);
    }
    
    static void disconnect(){
        currentUserProperty().setValue(null);
    }
    
    static boolean connect(String email, String password){
        User user = getUser(email, password);
        if(user != null){
            setCurrentUser(user);
            return true;
        }
        return false;     
    }
    
    static boolean exists(String email) {
        return allUsers.stream().anyMatch(x -> x.getEmail().equals(email));
    }

    
    //Not functional yet
 
    /**
     * default constructor
     * 
     */
    //public UsersManager(){};
    
    /**
     * other constructor : dependance injection by constructor
     * 
     * @param dm DataManager
     */
    /*public UsersManager(DataManager dm){
        setDataManager(dm);
        if(dataManager != null) {
            loadUsers();
        }
              
    }*/

    
}