package br.pucrs.facin.sispresc.dto;

/**
 * Created by lucas on 21/05/2017.
 */


public class UserDTO {

    private String username;
    private String password;
    private boolean authorized;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
