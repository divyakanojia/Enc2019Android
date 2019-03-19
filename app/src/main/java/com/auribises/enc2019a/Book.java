package com.auribises.enc2019a;

public class Book {

    public String name;
    public String author;
    public String price;

    public Book(){

    }

    public Book(String name, String author, String price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
