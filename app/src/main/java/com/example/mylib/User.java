package com.example.mylib;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Book> favorites = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();

    public User(){
        // Default constructor for DataSnapshot.getValue(User.class)
    }
    public User(String username,String password){
        this.username=username;
        this.password=password;
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
    public void addBook(Book book){
        books.add(book);
    }
    public void removeBook(Book book){
        books.remove(book);
    }
    public void addFavorite(Book book){
        favorites.add(book);
    }
    public void removeFavorite(Book book){
        favorites.remove(book);
    }
    public ArrayList<Book> getFavorites() {
        return favorites;
    }
    public ArrayList<Book> getBooks() {
        return books;
    }



}

