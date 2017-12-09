/*
 * Interface for data persistence class
 * 
 */
package business_logic;

import java.util.ArrayList;
import javafx.scene.control.TreeView;
/**
 *
 * @author fasimonet
 */
public interface DataManager {
    
    // Persistence for the users
    
   /**
    * recover persistent data
    * 
    * @return persistent user collection
    */
    public ArrayList<User> loadUsers();
    
    /**
    * save data for the users
    * 
    * @param user user collection to save
    */
    
    public void saveUsers(ArrayList<User> user);
        
    //Persistance for the Follows
    
   /**
    * recover persistent data
    * 
    * @return persistent follow collection
    */    
    public ArrayList<Follow> loadFollows();
    
    /**
    * save data for the follows
    * 
    * @param follow follow collection to save
    */ 
    public void saveFollows(ArrayList<Follow> follow);
}
