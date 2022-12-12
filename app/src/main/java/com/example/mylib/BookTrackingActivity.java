package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.DataBase.User;
import com.example.mylib.adapters.BookTrackAdapter;
import com.example.mylib.DataBase.Book;
import com.example.mylib.DataBase.FireBaseBook;
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
    HashMap<String,ArrayList<String>> borrowed=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tracking);

        DatabaseReference usersRef = new FireBaseUser().getUsersListRef();
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user= snapshot.getValue(User.class);
                    assert user != null;
                    for(String book: user.getBooks()){
                        if(!borrowed.containsKey(book)){
                            borrowed.put(book,new ArrayList<>());
                        }
                        borrowed.get(book).add(user.getUsername());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference booksRef = new FireBaseBook().getBookListRef();
        bookList = (ListView) findViewById(R.id.borrowedBookList);
        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book=snapshot.getValue(Book.class);
                    if(borrowed.containsKey(book.getName())){
                        books.add(book);
                    }
                }
                adapter = new BookTrackAdapter(BookTrackingActivity.this, books,borrowed);
                bookList.setAdapter(adapter);
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