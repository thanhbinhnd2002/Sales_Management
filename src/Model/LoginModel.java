package Model;

public class LoginModel {
    // attributes
    private String userName;
    private String password;

    // contructor
    public LoginModel(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

    // accessor and mutator methods
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
