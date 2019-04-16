package com.auribises.enc2019a.model;

public class User {

    public String name;
    public String email;
    public String password;
    public String token;

    public User() {
    }

    public User(String name, String email, String password, String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
