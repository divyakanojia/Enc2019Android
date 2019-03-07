package com.auribises.enc2019a;

import java.io.Serializable;

public class News implements Serializable{

    // Attributes
    public int image;
    public String title;
    public String url;

    public News(){

    }

    public News(int image, String title, String url) {
        this.image = image;
        this.title = title;
        this.url = url;
    }
}
