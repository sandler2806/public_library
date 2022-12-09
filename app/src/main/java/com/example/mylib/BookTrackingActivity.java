package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.Book;
import com.example.mylib.DataBase.FireBaseBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class BookTrackingActivity extends AppCompatActivity {

    static Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tracking);
    }


    public void searchBook(View view){
        TextView bookNameText = findViewById(R.id.bookName);
        String bookName=bookNameText.getText().toString();
        FireBaseBook fireBaseBook = new FireBaseBook();
        DatabaseReference bookRef = new FireBaseBook().getBookFromDB(bookName);
        bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                book = dataSnapshot.getValue(Book.class);
                Toast.makeText(BookTrackingActivity.this,book.getAuthor(),Toast.LENGTH_SHORT).show();
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void goBack(View view){
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }
}