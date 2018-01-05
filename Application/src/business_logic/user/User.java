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
     * User properties
     */
    private final StringProperty username = new SimpleStringProperty();
        @Override public StringProperty usernameProperty() {return username;}

    private final StringProperty password = new SimpleStringProperty();
        @Override public StringProperty passwordProperty() {return password;}

    private final StringProperty email = new SimpleStringProperty();
        @Override public StringProperty emailProperty() {return email;}

    private final ObjectProperty<Follow> userFollow = new SimpleObjectProperty<>();
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
     * @param username
     * @param password
     * @param email 
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
     * @return username 
     */
    @Override
    public String toString() {
        return getUsername();
    }
}
