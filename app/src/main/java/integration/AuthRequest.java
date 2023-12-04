package integration;

public class AuthRequest {
    private String login;
    private String password;

    public AuthRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getEmail() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
