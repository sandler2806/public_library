package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BorrowBook extends AppCompatActivity {

    ListView bookList;
    String[] books;
    String[] authors;
    int[] noOfCopies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);

        Resources res = getResources();
        bookList = (ListView) findViewById(R.id.bookList);
        books = res.getStringArray(R.array.books);
        authors = res.getStringArray(R.array.authors);
        noOfCopies = res.getIntArray(R.array.numberOfCopies);

        ItemAdapter adapter = new ItemAdapter(this,books, authors, noOfCopies);
        bookList.setAdapter(adapter);
    }

}