package com.example.nos.secureflomessaging.data;

/**
 * Created by Nos on 11/9/2016.
 */

public class User {
    private long id;
    private String password;
    private String user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setUser(String user) {
        this.user = user;
    }
}
