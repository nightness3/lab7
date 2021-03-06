package common;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User (String username) {
        this.username = username;
        this.password = "";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
