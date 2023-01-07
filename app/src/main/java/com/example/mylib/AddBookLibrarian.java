package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mylib.DataBase.FireBaseBook;


import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class AddBookLibrarian extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_librarian);
    }

    //general goBack button to return to librarian home
    public void goBack(View view) {
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }

    /**
     * Main function- adding book to library
     * First we receive input from librarian of all needed fields for books.
     * Then cast it to string
     * And finally add the book with received casted details to the firebase using the addBook method.
     *
     * @param view
     */
    public void addBook(View view) {
        TextView bookNameText = findViewById(R.id.BookNameInsert);
        TextView authorText = findViewById(R.id.BookAuthorInsert);
        TextView genreText = findViewById(R.id.BookGenreInsert);
        TextView amountText = findViewById(R.id.BookAmountsInsert);
        TextView publishingYearText = findViewById(R.id.BookYearPublishedInsert);
        String bookName = bookNameText.getText().toString();
        String author = authorText.getText().toString();
        String genre = genreText.getText().toString();
        int amount = Integer.parseInt(amountText.getText().toString());
        String publishingYear = publishingYearText.getText().toString();
        //Now will check if book exits already, if so will just add the amount to the existing book.
        FireBaseBook.addBook(bookName, author, genre, amount, publishingYear, this);


    }
}