/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import business_logic.Category;
import business_logic.User;
import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author fasimonet
 */

/*
 * I need to implements IUser IUser is not implemented yet so it will works soon but not yet 
*/
/*
public class XMLUsers implements IUSer, Serializable {
    public XMLUsers(User model){
        this.model = model;
        username = new SimpleStringProperty(model.getUsername());
        password = new SimpleStringProperty(model.getPassword());
        email = new SimpleStringProperty(model.getEmail());
        model.setMyFollow(new Category("Favorites"));
    }
    
    //not serializable
    private transient IUser model;  

    public IUser getModel() {
        return model;
    }
    
    private final StringProperty username;
        @Override StringProperty usernameProperty() {
            return model.usernameProperty();
        }
        @Override StringProperty emailProperty() {
            return model.emailProperty();
        }
        @Override ObjectProperty<Follow> userFollowProperty() {
            return model.userFollowProperty();
        }
                
    private final StringProperty email;
    private final StringProperty password;
    
    
}
*/