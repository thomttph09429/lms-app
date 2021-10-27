package com.poly.lmsapp.ui.model;

public class User {
    private int id ;
    private String username ;
    private String passworld ;

    public User(int id, String username, String passworld) {
        this.id = id;
        this.username = username;
        this.passworld = passworld;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }
}
