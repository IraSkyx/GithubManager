package business_logic.user;

import business_logic.repository.Category;
import business_logic.repository.Follow;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Business class : User
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
class User implements IUser {

    /**
     * User username property
     */
    private final StringProperty username = new SimpleStringProperty();
        /**
         * Get User username property
         * 
         * @return the StringProperty which corresponds to the user username
         */
        @Override public StringProperty usernameProperty() {return username;}
    
    /**
     * User password property
     */
    private final StringProperty password = new SimpleStringProperty();
        /**
         * Get User password property
         * 
         * @return the StringProperty which corresponds to the user password
         */
        @Override public StringProperty passwordProperty() {return password;}

    /**
     * User email property
     */
    private final StringProperty email = new SimpleStringProperty();
        /**
         * Get User email property
         * 
         * @return the StringProperty which corresponds to the email password
         */
        @Override public StringProperty emailProperty() {return email;}

    /**
     * User user Follow property
     */
    private final ObjectProperty<Follow> userFollow = new SimpleObjectProperty<>();
        /**
         * Get User userFollow property
         * 
         * @return the ObjectProperty which corresponds to the email password
         */
        @Override public ObjectProperty<Follow> userFollowProperty() {return userFollow;}

    /**
     * Default constructor : set empty attributes 
     */
    User() {
        setUsername("");
        setPassword("");
        setEmail("");
        setUserFollow(new Category("Favorites"));
    }

    /**
     * Constructor : set attributes at the values of parameters
     * 
     * @param username username of the User
     * @param password password of the User
     * @param email email of the User
     */

    User(String username, String password, String email) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setUserFollow(new Category("Favorites"));
    }

    /**
     * toString method
     *                                                                      
     * @return username the username of the User
     */
    @Override
    public String toString() {
        return getUsername();
    }
}
