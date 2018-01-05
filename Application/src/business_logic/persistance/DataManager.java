package business_logic.persistance;

import business_logic.user.IUser;
import java.util.List;

/**
 * Business interface for persistence management : API for serialisation
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public interface DataManager {
    
   /**
    * Recover persistent users
    * @return persistent user collection
    */
    public List<IUser> loadUsers();
    
    /**
    * Save users
    * @param users user collection to save
    */  
    public void saveUsers(List<IUser> users);        
}
