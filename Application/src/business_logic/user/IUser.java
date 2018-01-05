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
    
    /**
     * Get IUser username
     * 
     * @return the IUser username
     */
    default public String getUsername(){return usernameProperty().get();};
    /**
     * Set IUser username
     * 
     * @param value the username to be set
     */
    default public void setUsername(String value){usernameProperty().set(value);};   
    
    /**
     * Get IUser email
     * 
     * @return the IUser email
     */
    default public String getEmail(){return emailProperty().get();};
    /**
     * Set IUser email 
     * 
     * @param value the email to be set
     */
    default public void setEmail(String value){emailProperty().set(value);}; 
    
    /**
     * Get IUser password
     * 
     * @return the IUser password
     */
    default public String getPassword(){return passwordProperty().get();};
    /**
     * Set IUser password
     * 
     * @param value the IUser password
     */
    default public void setPassword(String value){passwordProperty().set(value);};  
    
    /**
     * Get IUser Follow
     * 
     * @return the IUser Follow
     */
    default public Follow getUserFollow(){return userFollowProperty().get();};
    /**
     * Set IUser Follow
     * 
     * @param value the IUserFollow to be set
     */
    default public void setUserFollow(Follow value){userFollowProperty().set(value);}; 
    
    /**
     * IUser username property
     * 
     * @return the StringProperty which corresponds to the user username
     */
    public StringProperty usernameProperty();
    /**
     * IUser email property
     * 
     * @return the StringProperty which corresponds to the user email
     */
    public StringProperty emailProperty();
    /**
     * IUser password property
     * 
     * @return the StringProperty which corresponds to the user password
     */
    public StringProperty passwordProperty();
    /**
     * IUser user Follow property
     * 
     * @return the ObjectProperty which corresponds to the user Follow
     */
    public ObjectProperty<Follow> userFollowProperty();
}
