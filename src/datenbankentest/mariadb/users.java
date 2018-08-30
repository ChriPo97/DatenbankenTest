/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbankentest.mariadb;

/**
 *
 * @author DatenbankenTest
 */

public class users {
    
    private int userId;
    private String login;
    private String password;
    
    public users() {};
    
    public users (String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    
}
