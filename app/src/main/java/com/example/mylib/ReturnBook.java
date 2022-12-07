package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.mylib.adapters.BookAdapter;
import com.example.mylib.adapters.ReturnBookAdapter;

public class ReturnBook extends AppCompatActivity {
    ListView bookList;
    String[] books;
    String[] authors;
    int[] noOfCopies;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);

        bookList = (ListView) findViewById(R.id.bookList);
        Book[] books = new Book[7];
        books[0] = new Book("Harry potter 1", "J.K rolling","", "",1 );
        books[1] = new Book("Harry potter 2", "J.K rolling","", "",1 );
        books[2] = new Book("Harry potter 3", "J.K rolling","", "",1 );
        books[3] = new Book("Harry potter 4", "J.K rolling","", "",1 );
        books[4] = new Book("Harry potter 5", "J.K rolling","", "",1 );
        books[5] = new Book("Harry potter 6", "J.K rolling","", "",1 );
        books[6] = new Book("Harry potter 7", "J.K rolling","", "",1 );
        ReturnBookAdapter adapter = new ReturnBookAdapter(this, books);
        bookList.setAdapter(adapter);
    }
    public void returnBook(View view){

    }
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}