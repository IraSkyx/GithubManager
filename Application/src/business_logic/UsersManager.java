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
   
    private static final ObjectProperty<User> currentUser = new SimpleObjectProperty();
        public static User getCurrentUser() {return currentUser.get();}
        public static void setCurrentUser(User user) {currentUser.set(user);}
        public static ObjectProperty<User> currentUserProperty(){return currentUser;}
    
    private static final ArrayList<User> allUsers = new ArrayList<>();

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }
    
    public static User getUser(String username){
        return allUsers.stream().filter(x -> x.getUsername().equals(username)).findFirst().get();
    }
}
