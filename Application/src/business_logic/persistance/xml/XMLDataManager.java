package business_logic.persistance.xml;

import business_logic.persistance.DataManager;
import business_logic.user.IUser;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import launch.Main;

/**
 * Business class for XML persistance management : DataManager to manager XML persistance
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class XMLDataManager implements DataManager {  
    /**
     * Load all the IUsers
     * 
     * @return the list of IUsers loaded
     */
    @Override
    public List<IUser> loadUsers() {
        List<IUser> result = null;
        try (XMLDecoder ois = new XMLDecoder(new FileInputStream("users.xml"))) {
            result = ((ArrayList<XMLUser>)ois.readObject()).stream().map(n -> n.getModel()).collect(Collectors.toList());
        } 
        catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    /**
     * Save users using XML serialization
     * 
     * @param users all the IUsers to serialize
     */
    @Override
    public void saveUsers(List<IUser> users) {
        try (XMLEncoder oos = new XMLEncoder(new FileOutputStream("users.xml"))) {
            List<XMLUser> bn = users.stream().map(n -> new XMLUser(n)).collect(Collectors.toList());
            oos.writeObject(bn);
        }
        catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }    
    }
}
