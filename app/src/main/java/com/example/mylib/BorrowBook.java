package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylib.Objects.Book;
import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.adapters.BookAdapter;

import java.util.ArrayList;


public class BorrowBook extends AppCompatActivity {

    ListView bookList;
    BookAdapter adapter;
    ArrayList<Book> books = new ArrayList<>();
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);
        bookList = (ListView) findViewById(R.id.bookList);

        FireBaseBook.showAvailableBooks(this,bookList);
    }

    public void borrow(View view){
        View parentView = (View)view.getParent();
        // Access the data associated with the list item, such as the book name and author
        TextView bookNameView = parentView.findViewById(R.id.bookNameTextView);
        TextView amountText = parentView.findViewById(R.id.noOfCopiesTextView);
        String bookName = bookNameView.getText().toString();
        int amount=Integer.parseInt(amountText.getText().toString().substring(18));
        FireBaseUser.addToBorrowed(bookName,amount,BorrowBook.this);
    }
}