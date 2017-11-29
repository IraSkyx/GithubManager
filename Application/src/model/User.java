package model;

public class User {

    public User(String password, String email) {
        this.password = password;
        this.email = email;
        this.follows = new Category("Favorites");
    }
	
    private String username;
    private String password;
    private String email;
    private final Follow follows;

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
