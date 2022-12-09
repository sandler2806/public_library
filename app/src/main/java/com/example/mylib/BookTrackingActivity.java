package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.adapters.BookAdapter;
import com.example.mylib.adapters.BookTrackAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BookTrackingActivity extends AppCompatActivity {

    ListView bookList;
    BookTrackAdapter adapter;
    ArrayList<Book> books = new ArrayList<>();
//    HashMap<String<ArrayList<String>>> borrowed=new HashMap<>();
    static Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tracking);

        DatabaseReference booksRef = new FireBaseBook().getBookListRef();
        bookList = (ListView) findViewById(R.id.borrowedBookList);
        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    books.add(snapshot.getValue(Book.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        DatabaseReference usersRef = new FireBaseUser().getUsersListRef();
        bookList = (ListView) findViewById(R.id.borrowedBookList);
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    books.add(snapshot.getValue(Book.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        adapter = new BookTrackAdapter(BookTrackingActivity.this, books);
        bookList.setAdapter(adapter);
    }


//    public void searchBook(View view){
//        TextView bookNameText = findViewById(R.id.bookNameTextView);
//        String bookName=bookNameText.getText().toString();
//        FireBaseBook fireBaseBook = new FireBaseBook();
//        DatabaseReference bookRef = new FireBaseBook().getBookFromDB(bookName);
//        bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                book = dataSnapshot.getValue(Book.class);
//                Toast.makeText(BookTrackingActivity.this,book.getAuthor(),Toast.LENGTH_SHORT).show();
//                }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });
//    }

    public void goBack(View view){
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }
}