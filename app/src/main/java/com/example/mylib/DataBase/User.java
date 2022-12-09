package com.example.mylib.DataBase;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String name;
    private String phone;
    private ArrayList<Book> favorites = new ArrayList<>();
    private ArrayList<String> books = new ArrayList<>();

    public User(){
        // Default constructor for DataSnapshot.getValue(User.class)
    }
    public User(User user){
        username=user.username;
        password=user.password;
        name=user.name;
        phone=user.phone;
        favorites.addAll(user.favorites);
        books.addAll(user.books);
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

    public void setPassword(String password) {
        this.password = password;
    }
    public void addBook(Book book){
        books.add(book.getName());
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

    public ArrayList<Book> getFavorites() {
        return favorites;
    }
    public ArrayList<String> getBooks() {
        return books;
    }



}

