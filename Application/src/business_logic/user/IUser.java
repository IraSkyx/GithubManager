package business_logic.user;

import business_logic.repository.Follow;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 * Business interface : IUser : API for User
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public interface IUser {
    
    default public String getUsername(){return usernameProperty().get();};
    default public void setUsername(String value){usernameProperty().set(value);};   
    
    default public String getEmail(){return emailProperty().get();};
    default public void setEmail(String value){emailProperty().set(value);}; 
    
    default public String getPassword(){return passwordProperty().get();};
    default public void setPassword(String value){passwordProperty().set(value);};  
    
    default public Follow getUserFollow(){return userFollowProperty().get();};
    default public void setUserFollow(Follow value){userFollowProperty().set(value);}; 
    
    public StringProperty usernameProperty();
    public StringProperty emailProperty();
    public StringProperty passwordProperty();
    public ObjectProperty<Follow> userFollowProperty();
}
