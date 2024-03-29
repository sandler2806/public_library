package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.R;

public class ReturnBookActivity extends AppCompatActivity {
    ListView bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);
        //show all the user's books
        bookList = (ListView) findViewById(R.id.bookList);
        FireBaseUser.createBookListForReturn(bookList, ReturnBookActivity.this, "");
    }

    public void search(View view) {
        TextView bookNameText = findViewById(R.id.bookName);
        String bookName = bookNameText.getText().toString();
        FireBaseUser.createBookListForReturn(bookList, ReturnBookActivity.this, bookName);
    }


    public void returnBook(View view) {
        //get data from text views
        View parentView = (View) view.getParent();
        TextView bookIdView = parentView.findViewById(R.id.bookId);
        String bookId = bookIdView.getText().toString();
        //remove the book from the user's list
        FireBaseUser.removeFromBorrowed(bookId);
        // take the reference to the book and set the amount by amount + 1 for return logic
        FireBaseBook.returnBook(bookId, ReturnBookActivity.this);
    }

    public void goBack(View view) {
        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}