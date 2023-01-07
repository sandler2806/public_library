package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.DataBase.FireBaseUser;


//This class represents the Borrowing act
//The class using 2 layouts file to represent the data to the use
//1) activity_borrow_book: contain a single ListView each represent a book
//2) book_list_detail: contain the design of each item on the list
public class BorrowBook extends AppCompatActivity {


    ListView bookList;

    public void goBack(View view) {
        startActivity(new Intent(this, ClientHomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);
        bookList = findViewById(R.id.bookList);
        //Fill the ListView with the available books
        FireBaseBook.showAvailableBooks(this, bookList, "");
    }

    public void search(View view) {
        TextView bookNameText = findViewById(R.id.bookName);
        String bookName = bookNameText.getText().toString();
        FireBaseBook.showAvailableBooks(this, bookList, bookName);
    }

    public void borrow(View view) {
        View parentView = (View) view.getParent();
        // Access the data associated with the list item, such as the book name and author
        TextView bookIdView = parentView.findViewById(R.id.bookId);
        TextView amountText = parentView.findViewById(R.id.noOfCopiesTextView);
        String bookId = bookIdView.getText().toString();
        int amount = Integer.parseInt(amountText.getText().toString().substring(18));
        FireBaseUser.addToBorrowed(bookId, amount, BorrowBook.this);
    }
}