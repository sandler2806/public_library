package com.example.mylib.Objects;

import com.example.mylib.DataBase.FireBaseBook;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Book {


    private int amount;
    private String name;
    private String author;
    private String genre;
    private String publishing_year;
    private int id;
    private User owner;

    public Book(String bookName) {
        FireBaseBook fireBaseBook = new FireBaseBook();
        Task<DataSnapshot> task = fireBaseBook.getBookFromDB(bookName).get();
        try {
            DataSnapshot result = task.getResult();
            // do something with the result
            if(result.getValue()!=null){
                Map<String, Object> bookData= (Map<String, Object>)result.getValue();
                Long bookAmount = (Long) bookData.get("amount");
                this.amount = bookAmount.intValue();
                this.name = (String) bookData.get("name");
                this.author = (String) bookData.get("author");
                this.genre = (String) bookData.get("genre");
                this.publishing_year = (String) bookData.get("publishing_year");
            }
        } catch (Exception e) {
            // handle the exception
        }
//        FireBaseBook.Await(task);
//        if(task.getResult().getValue()!=null){
//            Map<String, Object> bookData= (Map<String, Object>)task.getResult().getValue();
//            Long bookAmount = (Long) bookData.get("amount");
//            this.amount = bookAmount.intValue();
//            this.name = (String) bookData.get("name");
//            this.author = (String) bookData.get("author");
//            this.genre = (String) bookData.get("genre");
//            this.publishing_year = (String) bookData.get("publishing_year");
//        }
    }
    public Book(){
        // Default constructor for DataSnapshot.getValue(User.class)
    }
    public Book(String name, String author, String genre, String publishing_year,int amount) {
        this.amount=amount;
        owner=null;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publishing_year = publishing_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublishing_year() {
        return publishing_year;
    }

    public void setPublishing_year(String publishing_year) {
        this.publishing_year = publishing_year;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
