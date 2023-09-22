package UserLoginSystem;

public class AccountUser {
    private String Username;
    private String Password;

    public AccountUser(String username, String password) {
        Username = username;
        Password = password;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
