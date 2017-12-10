package business_logic;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/*
* Class User : user has access to more functionalities than a visitor
*/

public class User {
    
    private String username;
    private String password;
    private String email;
    private final ObjectProperty<Follow> myFollow = new SimpleObjectProperty<>();
    public Follow getMyFollow() {return myFollow.get();}
    public void setMyFollow(Follow myFollow) {this.myFollow.set(myFollow);}
    public ObjectProperty<Follow> myFollowProperty(){return myFollow;}
    
   
    //
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        setMyFollow(new Category("Favorites"));
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return username;
    }
}
