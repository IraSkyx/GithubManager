package business_logic;

public class User {
    
    private String username;
    private String password;
    private String email;
    private final Follow follows;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.follows = new Category("Favorites");
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

    public Follow getFollows() {
        return follows;
    }
	
}
