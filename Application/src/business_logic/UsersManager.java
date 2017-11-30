/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import java.util.ArrayList;

/**
 *
 * @author Adrien
 */
public class UsersManager {
    private static final ArrayList<User> AllUsers = new ArrayList<>();

    public static ArrayList<User> getAllUsers() {
        return AllUsers;
    }
    
    public static User getUser(String username){
        return AllUsers.stream().filter(x -> x.getUsername().equals(username)).findFirst().get();
    }
}
