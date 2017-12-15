package business_logic.persistance.xml;

import business_logic.gateways.APIManager;
import business_logic.persistance.DataManager;
import business_logic.user.IUser;
import business_logic.user.UsersManager;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author fasimonet
 */
public class XMLUsers implements DataManager, Serializable {

    /**
     * Load users using XML deserialization
     * @return Every loaded users
     */
    
    private APIManager apiManager;
    
    public XMLUsers(APIManager apiManager){
        this.apiManager = apiManager;
    }
    
    public void setAPIManager(APIManager apiManager){
        this.apiManager = apiManager;
    }
    
    @Override
    public ArrayList<IUser> loadUsers() {
        ArrayList<IUser> result = null;
        try (XMLDecoder ois = new XMLDecoder(new FileInputStream("users.xml"))) {
            result = (ArrayList<IUser>)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * Save users using XML serialization
     * @param users All the users to serialize
     */
    @Override
    public void saveUsers(ArrayList<IUser> users) {
        try (XMLEncoder oos = new XMLEncoder(new FileOutputStream("users.xml"))) {
            oos.writeObject(UsersManager.getAllUsers());        
        }catch (IOException e) {
            e.printStackTrace();
        }      
    }
}
