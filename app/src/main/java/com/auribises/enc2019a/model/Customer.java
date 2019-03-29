package com.auribises.enc2019a.model;

import java.io.Serializable;

// MODEL OR BEAN OR POJO
public class Customer implements Serializable{

    public int id;
    public String name;
    public String phone;
    public String email;

    public Customer(){

    }

    public Customer(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        String message = "Name: "+name+"\nPhone: "+phone+"\nEmail: "+email;
        return message;
    }
}
