package com.auribises.enc2019a;

import java.io.Serializable;

public class Person implements Serializable{

    public String name;
    public String phone;

    public Person(){

    }

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

