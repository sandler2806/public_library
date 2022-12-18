package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.DataBase.FireBaseUser;

public class ReturnBook extends AppCompatActivity {
    ListView bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);
        bookList = (ListView) findViewById(R.id.bookList);
        FireBaseUser.createBookListForReturn(bookList,ReturnBook.this);
    }

    public void returnBook(View view){

        View parentView = (View)view.getParent();
        TextView bookNameView = parentView.findViewById(R.id.bookNameTextView);
        String bookName = bookNameView.getText().toString();
        // take the reference to the book and set the amount by amount + 1 for return logic
        FireBaseUser.removeFromBorrowed(bookName);
        FireBaseBook.returnBook(bookName,ReturnBook.this);
    }
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}