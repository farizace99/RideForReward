package com.myapplicationdev.android.rideforreward;

import java.io.Serializable;

public class Member implements Serializable {

    private String Email;
    private String Password;
    private int Points;

    public Member(String email, String password, int points) {
        Email = email;
        Password = password;
        Points = points;
    }

    public Member() {
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
