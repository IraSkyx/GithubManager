package business_logic.persistance;

import business_logic.user.IUser;
import java.util.ArrayList;

/**
 * Interface for data persistence class
 * @author fasimonet
 */
public interface DataManager {
    
   /**
    * Recover persistent users
    * @return persistent user collection
    */
    public ArrayList<IUser> loadUsers();
    
    /**
    * Save users
    * @param users user collection to save
    */  
    public void saveUsers(ArrayList<IUser> users);        
}
