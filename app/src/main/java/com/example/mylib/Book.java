package com.example.mylib;

public class Book {

    public String name;
    public String Author;

    public Book() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Book(String username, String email) {
        this.name = username;
        this.Author = email;
    }

}
