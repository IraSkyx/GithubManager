package business_logic.persistance.xml;

import business_logic.repository.Follow;
import business_logic.user.IUser;
import business_logic.user.UserFactory;
import java.io.Serializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Business class for XML persistance management : Serialisable User used as a proxy to serialise IUser
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class XMLUser implements IUser, Serializable {
    public XMLUser(){
        this(UserFactory.create());
    }
    
    public XMLUser(IUser model) {
        this.model = model;
        username = new SimpleStringProperty(model.getUsername());
        password = new SimpleStringProperty(model.getPassword());
        email = new SimpleStringProperty(model.getEmail());
        userFollow = new SimpleObjectProperty(model.getUserFollow());
    }
            
    private transient IUser model;  

    public IUser getModel() {
        return model;
    }

    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty email;
    private final ObjectProperty<Follow> userFollow;
    
    @Override public StringProperty usernameProperty() {return username;}
    @Override public StringProperty passwordProperty() {return password;}
    @Override public StringProperty emailProperty() {return email;}
    @Override public ObjectProperty<Follow> userFollowProperty() {return userFollow;}
    
    @Override public String getUsername() {return model.getUsername();}
    @Override public String getPassword() {return model.getPassword();}
    @Override public String getEmail() {return model.getEmail();}
    @Override public Follow getUserFollow() {return model.getUserFollow();}
    
    @Override public void setUsername(String value) {model.setUsername(value);}
    @Override public void setPassword(String value) {model.setPassword(value);}
    @Override public void setEmail(String value) {model.setEmail(value);}
    @Override public void setUserFollow(Follow value) {model.setUserFollow(value);}
}