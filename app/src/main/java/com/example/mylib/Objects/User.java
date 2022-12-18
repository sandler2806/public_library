package com.example.mylib.Objects;

import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.Objects.Book;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Map;

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
    public User(String username) {
//        FireBaseUser fireBaseUser = new FireBaseUser();
        Task<DataSnapshot> task = FireBaseUser.getUserFromDB(username).get();
        FireBaseUser.Await(task);
        if(task.getResult().getValue()!=null){
            Map<String, Object> userData= (Map<String, Object>)task.getResult().getValue();
            this.books = (ArrayList<String>) userData.get("books");
            this.name = (String) userData.get("name");
            this.username = (String) userData.get("username");
            this.password = (String) userData.get("password");
            this.phone = (String) userData.get("phone");
            this.favorites = (ArrayList<Book>) userData.get("favorites");
        }
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

    public void setBooks(ArrayList<String> books){
        this.books = books;
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

