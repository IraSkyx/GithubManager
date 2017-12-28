package business_logic.persistance.xml;

import business_logic.repository.Category;
import business_logic.repository.Follow;
import business_logic.user.IUser;
import business_logic.user.UserFactory;
import java.io.Serializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author fasimonet
 */
public class XMLUser implements IUser, Serializable {
    public XMLUser(){
        this(UserFactory.make());
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
    
    @Override public String getUsername() {return IUser.super.getUsername();}
    @Override public String getPassword() {return IUser.super.getPassword();}
    @Override public String getEmail() {return IUser.super.getEmail();}
    @Override public Follow getUserFollow() {return IUser.super.getUserFollow();}
    
    @Override public void setUsername(String value) {IUser.super.setUsername(value);}
    @Override public void setPassword(String value) {IUser.super.setPassword(value);}
    @Override public void setEmail(String value) {IUser.super.setEmail(value);}
    @Override public void setUserFollow(Follow value) {IUser.super.setUserFollow(value);}
}