package com.example.activitydemo.bean;

import java.io.Serializable;

public class User implements Serializable {
    private String account;
    private String password;

    public User(String account,String password){
        this.account = account;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
