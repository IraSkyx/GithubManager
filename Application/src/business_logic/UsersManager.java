/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import stub.StubUser;

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

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }
    
    public static void setAllUsers(ArrayList<User> allUsers) {
        UsersManager.allUsers = allUsers;
    }
    
    public static User getUser(String email, String password){
        return allUsers.stream().filter(x -> x.getEmail().equals(email)).findFirst().orElse(null);
    }
    
    public static void disconnect(){
        currentUserProperty().setValue(null);
    }
    
    public static boolean connect(String email, String password){
        User user = getUser(email, password);
        if(user != null){
            setCurrentUser(user);
            return true;
        }
        return false;     
    }
    
    private static boolean exists(String email) {
        return allUsers.stream().anyMatch(x -> x.getEmail().equals(email));
    }
    
    public static User signUp(String username, String email, String password, String verifPassword) throws InvalidParameterException {
        if(!password.equals(verifPassword))
            throw new InvalidParameterException("Password mismatch");      
        
        if(exists(email))
            throw new InvalidParameterException("Email already used");
        
        User user = new User(username, password, email);
        allUsers.add(user);
        return user;
    }
}
