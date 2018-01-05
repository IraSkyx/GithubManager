package business_logic.user;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

/**
 * Business class : UserFactory : Factory to create IUser
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class UserFactory {

    private static final String isValidEmail = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

    public static IUser create(String username, String email, String password, String verifPassword) throws InvalidParameterException {

        if(!password.equals(verifPassword))
            throw new InvalidParameterException("Password mismatch");

        if(!Pattern.compile(isValidEmail).matcher(email).matches())
            throw new InvalidParameterException("Email is not valid");

        if(username.length() < 5)
            throw new InvalidParameterException("Username's too short (5 characters min)");

        if(password.length() < 3)
            throw new InvalidParameterException("Password's too short (5 characters min)");

        if(UsersManager.exists(email))
            throw new InvalidParameterException("Email already used");

        User user = new User(username, password, email);
        UsersManager.getAllUsers().add(user);
        return user;
    }

    public static IUser create(String username, String email, String password) {

        if(!Pattern.compile(isValidEmail).matcher(email).matches())
            throw new InvalidParameterException("Email is not valid");

        if(username.length() < 5)
            throw new InvalidParameterException("Username's too short (5 characters min)");

        if(password.length() < 3)
            throw new InvalidParameterException("Password's too short (5 characters min)");

        if(UsersManager.exists(email))
            throw new InvalidParameterException("Email already used");

        User user = new User(username, password, email);
        UsersManager.getAllUsers().add(user);
        return user;
    }

    public static IUser create(){
        return new User();
    }
}
