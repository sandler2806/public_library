package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BorrowBook extends AppCompatActivity {

    ListView bookList;
    String[] books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);

        Resources res = getResources();
        bookList = (ListView) findViewById(R.id.bookList);
        books = res.getStringArray(R.array.books);

        bookList.setAdapter(new ArrayAdapter<String>(this, R.layout.book_list_detail,books));
    }

}