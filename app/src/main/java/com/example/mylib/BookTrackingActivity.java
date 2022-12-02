package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BookTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tracking);
    }

    public void searchBook(View view){
        TextView bookNameText = findViewById(R.id.bookName);
        String bookName=bookNameText.getText().toString();
//        FireBaseBook.searchBook(bookName);

    }
    public void goBack(View view){
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }
}