package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.Objects.Book;
import com.example.mylib.R;


public class RemoveBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);
    }

    public void goBack(View view) {
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }

    public void removeBook(View view) {
        //get data from text views
        TextView bookNameText = findViewById(R.id.BookNameInsert);
        TextView amountText = findViewById(R.id.BookAmountsInsert);
        TextView bookAuthorText = findViewById(R.id.BookAuthor);
        TextView bookYearPublishedText = findViewById(R.id.BookYearPublished);
        TextView bookGenreText = findViewById(R.id.BookGenre);
        String bookName = bookNameText.getText().toString();
        String bookAuthor = bookAuthorText.getText().toString();
        String bookYearPublished = bookYearPublishedText.getText().toString();
        String bookGenre = bookGenreText.getText().toString();
        int amount = Integer.parseInt(amountText.getText().toString());
        //remove book from database
        Book book = new Book(bookName, bookAuthor, bookGenre, bookYearPublished, amount);
        FireBaseBook.removeBook(RemoveBookActivity.this, book);
    }
}