package com.example.remove.LitePalTest;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {
    private int id;
    private String username;
    private String password;
    private String othername;
    private String userimage;
    private String gmail;

    public User(String username, String password, String othername, String gmail) {
        this.username = username;
        this.password = password;
        this.othername = othername;
        this.gmail = gmail;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
