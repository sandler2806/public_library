package com.example.mylib.Objects;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.converter.gson.GsonConverterFactory;

public class User {
    private String username;
    private String password;
    private String name;
    private String phone;
    private ArrayList<BorrowedBook> books = new ArrayList<>();

    public User() {
        // Default constructor for DataSnapshot.getValue(User.class)
    }

    public User(String username, String password, String name, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.books = new ArrayList<>();
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

    public void setBooks(ArrayList<BorrowedBook> books) {
        this.books = books;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void removeBook(Book book) {
        books.remove(book);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<BorrowedBook> getBooks() {
        return books;
    }

}

