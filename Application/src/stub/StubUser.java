/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business_logic.User;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author adlenoir
 */
public class StubUser {
    public static ArrayList<User> loadUsers(){
        return Arrays.(
            new User("Adrien", "Lenoir", "adrien.lenoir@etu.uca.fr"),
            new User("Fabien", "Simonet", "fabien.simonet@etu.uca.fr")    
        );
    }
}
