package business_logic.persistance.xml;

import business_logic.gateways.APIManager;
import business_logic.persistance.DataManager;
import business_logic.repository.Follow;
import business_logic.user.IUser;
import business_logic.user.UsersManager;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import business_logic.user.UserFactory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author fasimonet
 */
public class XMLUsers implements IUser, Serializable {

    public XMLUsers(){
        UserFactory.make();
    }
    
    public XMLUsers(IUser model){
        this.model = model;
        StringProperty username = new SimpleStringProperty(model.getUsername());
        StringProperty password = new SimpleStringProperty(model.getPassword());
        StringProperty email = new SimpleStringProperty(model.getEmail());
        ObjectProperty<Follow> userFollow = new SimpleObjectProperty<>(model.getUserFollow());
    }
    
    private transient IUser model;  
    
    public IUser getModel() {
        return model;
    }
    
    @Override
    public String getUsername() {
        return IUser.super.getUsername(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUsername(String value) {
        IUser.super.setUsername(value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        return IUser.super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(String value) {
        IUser.super.setEmail(value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        return IUser.super.getPassword(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPassword(String value) {
        IUser.super.setPassword(value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Follow getUserFollow() {
        return IUser.super.getUserFollow(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUserFollow(Follow value) {
        IUser.super.setUserFollow(value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StringProperty usernameProperty() {
        return model.usernameProperty();
    }

    @Override
    public StringProperty emailProperty() {
        return model.emailProperty();
    }

    @Override
    public StringProperty passwordProperty() {
        return model.passwordProperty();
    }

    @Override
    public ObjectProperty<Follow> userFollowProperty() {
        return model.userFollowProperty();
    }
}