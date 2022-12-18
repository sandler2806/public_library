package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.example.mylib.DataBase.FireBaseBook;

public class BookTrackingActivity extends AppCompatActivity {

    ListView bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tracking);
        bookList = (ListView) findViewById(R.id.borrowedBookList);
//        show list of all borrowed books
        FireBaseBook.showBorrowedBooks(this,bookList);
    }

    public void goBack(View view){
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }
}