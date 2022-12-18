package com.example.mylib.Objects;

public class Book {


    private int amount;
    private String name;
    private String author;
    private String genre;
    private String publishingYear;

    public Book(){
        // Default constructor for DataSnapshot.getValue(User.class)
    }
    public Book(String name, String author, String genre, String publishingYear,int amount) {
        this.amount=amount;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publishingYear = publishingYear;
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

    public String getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(String publishingYear) {
        this.publishingYear = publishingYear;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
