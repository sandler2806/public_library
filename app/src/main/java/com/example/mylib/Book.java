package com.example.mylib;

public class Book {


    private int amount;
    private String name;
    private String author;
    private String genre;
    private String publishing_year;
    private int id;
    private User owner;

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
