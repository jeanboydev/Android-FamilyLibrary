package cn.edu.pku.app.familylibrary.model;

import cn.edu.pku.app.familylibrary.constant.Gender;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class Admin extends User {

    private String username;
    private String password;

    public Admin() {
    }

    public Admin(String realName, Gender gender, String contact, String note, String username, String password) {
        super(realName, gender, contact, note);
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
